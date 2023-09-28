package net.xeill.elpuig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ShowDepts {

    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccessFileDepartments = new RandomAccessFile(new File("departaments.bin"), "r");
            RandomAccessFile randomAccessFileWorkers = new RandomAccessFile(new File("workers.txt"), "r");

            int departmentId, departmentPosition = 0;
            char departmentName[] = new char[10];
            int workerdDepartmentId, workerPosition;
            char auxWorker;


            do {
                randomAccessFileDepartments.seek(departmentPosition);
                departmentId = randomAccessFileDepartments.readInt();

                if (departmentId != 0) {
                    for (int i = 0; i < departmentName.length ; i++) {
                        departmentName[i] = randomAccessFileDepartments.readChar();
                    }

                    String departmentText = new String(departmentName);
                    System.out.println("DepartmentId: "+departmentId+"\t department: "+departmentText);
                    workerPosition = 0;

                    do {
                        randomAccessFileWorkers.seek(workerPosition);
                        StringBuilder sb = new StringBuilder(1000);
                        sb.append("\t workerId:");
                        sb.append(randomAccessFileWorkers.readInt());
                        sb.append("\t workerName:");
                        for (int i = 0; i < 10; i++) {
                            auxWorker = randomAccessFileWorkers.readChar();
                            if ((int) auxWorker != 0) {
                                sb.append(auxWorker);
                            }
                        }
                        sb.append("\t Salary:");
                        workerdDepartmentId = randomAccessFileWorkers.readInt();
                        sb.append(randomAccessFileWorkers.readDouble());
                        if (departmentId == workerdDepartmentId) {
                            System.out.println(sb.toString());
                        }

                        workerPosition = workerPosition + 36;

                    } while (randomAccessFileWorkers.getFilePointer() < randomAccessFileWorkers.length());
                    System.out.println("_____________________________________________________________");
                }
                departmentPosition = departmentPosition + 24;

            } while (randomAccessFileDepartments.getFilePointer() < randomAccessFileDepartments.length());

            randomAccessFileWorkers.close();
            randomAccessFileDepartments.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
