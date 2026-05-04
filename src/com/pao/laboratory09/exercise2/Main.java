package src.com.pao.laboratory09.exercise2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        // Ensure the output directory exists
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        // 1. Citește N din stdin
        int n = scanner.nextInt();

        // 2. Scrie toate înregistrările în OUTPUT_FILE cu DataOutputStream
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            for (int i = 0; i < n; i++) {
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                String tip = scanner.next();

                byte[] idBytes = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(id).array();
                dos.write(idBytes);

                byte[] sumaBytes = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(suma).array();
                dos.write(sumaBytes);

                byte[] dataBytes = new byte[10];
                Arrays.fill(dataBytes, (byte) ' ');
                byte[] strBytes = data.getBytes("US-ASCII");
                System.arraycopy(strBytes, 0, dataBytes, 0, Math.min(strBytes.length, 10));
                dos.write(dataBytes);

                byte tipByte = (byte) (tip.equals("DEBIT") ? 1 : 0);
                dos.write(tipByte);

                // Initially all states are PENDING (0)
                dos.write((byte) 0);

                // bytes 24-31: padding
                dos.write(new byte[8]);
            }
        }

        // 3. Procesează comenzile din stdin până la EOF cu RandomAccessFile
        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (scanner.hasNext()) {
                String cmd = scanner.next();

                if (cmd.equals("READ")) {
                    int idx = scanner.nextInt();
                    printRecord(raf, idx);
                }
                else if (cmd.equals("UPDATE")) {
                    int idx = scanner.nextInt();
                    String statusStr = scanner.next();

                    byte statusByte = 0;
                    if (statusStr.equals("PROCESSED")) statusByte = 1;
                    else if (statusStr.equals("REJECTED")) statusByte = 2;

                    // seek(idx * RECORD_SIZE + 23), scrie noul status
                    raf.seek(idx * RECORD_SIZE + 23);
                    raf.write(statusByte);
                    System.out.println("Updated [" + idx + "]: " + statusStr);
                }
                else if (cmd.equals("PRINT_ALL")) {
                    // citește și afișează toate înregistrările
                    long count = raf.length() / RECORD_SIZE;
                    for (int i = 0; i < count; i++) {
                        printRecord(raf, i);
                    }
                }
            }
        }
    }

    private static void printRecord(RandomAccessFile raf, int idx) throws IOException {
        raf.seek(idx * RECORD_SIZE);
        byte[] record = new byte[RECORD_SIZE];
        raf.readFully(record);

        ByteBuffer buffer = ByteBuffer.wrap(record).order(ByteOrder.LITTLE_ENDIAN);

        int id = buffer.getInt();
        double suma = buffer.getDouble();

        byte[] dataBytes = new byte[10];
        buffer.get(dataBytes);
        String data = new String(dataBytes, "US-ASCII").trim(); // Remove padding spaces

        byte tipByte = buffer.get();
        String tip = tipByte == 1 ? "DEBIT" : "CREDIT";

        byte statusByte = buffer.get();
        String status = "PENDING";
        if (statusByte == 1) status = "PROCESSED";
        else if (statusByte == 2) status = "REJECTED";

        // Format linie output: [idx] id=<id> data=<data> tip=<CREDIT|DEBIT> suma=<suma:.2f> RON status=<STATUS>
        System.out.printf(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s\n",
                idx, id, data, tip, suma, status);
    }
}