package util;

import java.util.Scanner;
import services.StrukturGraph;

public class GraphMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StrukturGraph graph = new StrukturGraph();
        
        while (true) {

                System.out.println("\n╔════════════════════════════════════╗");
                System.out.println("║       Menu Logistik Apotek         ║");
                System.out.println("╠════════════════════════════════════╣");
                System.out.println("║ [1] Tambahkan Lokasi               ║");
                System.out.println("║ [2] Hubungkan Lokasi               ║");
                System.out.println("║ [3] Ubah Jarak Lokasi              ║");
                System.out.println("║ [4] Tampilkan Peta Logistik        ║");
                System.out.println("║ [5] Cari Jalur Terpendek           ║");
                System.out.println("║ [0] Keluar                         ║");
                System.out.println("╚════════════════════════════════════╝");
                
            int choice = -1;
            while (true) {
                try {
                	System.out.print("Pilih: ");
                    choice = Integer.parseInt(scanner.nextLine()); // Membaca input sebagai String terus di-parse
                    break; // Keluar dari loop kalo input valid
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka!");
                }
            }
            
            switch (choice) {
                case 1:
                    String nodeName;
                    do {
                    	System.out.println(" === Tambahkan Lokasi === ");
                        System.out.print("Nama Lokasi: ");
                        nodeName = scanner.nextLine().trim();
                        if (nodeName.isEmpty()) {
                            System.out.println("Nama lokasi tidak boleh kosong!");
                        }
                    } while (nodeName.isEmpty());
                    graph.addNode(nodeName);
                    break;
                case 2:
                    String fromNode, toNode;
                    do {
                    	System.out.println(" === Hubungkan Lokasi === ");
                        System.out.print("Dari: ");
                        fromNode = scanner.nextLine().trim();
                        if (fromNode.isEmpty()) {
                            System.out.println("Nama lokasi asal tidak boleh kosong!");
                        }
                    } while (fromNode.isEmpty());

                    do {
                        System.out.print("Ke: ");
                        toNode = scanner.nextLine().trim();
                        if (toNode.isEmpty()) {
                            System.out.println("Nama lokasi tujuan tidak boleh kosong!");
                        }
                    } while (toNode.isEmpty());

                    int weight = -1;
                    while (weight < 0) {
                        System.out.print("Jarak (angka, >0): ");
                        String wInput = scanner.nextLine();
                        try {
                            weight = Integer.parseInt(wInput);
                            if (weight < 0) {
                                System.out.println("Jarak harus angka positif!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Input jarak tidak valid!");
                        }
                    }
                    graph.addEdge(fromNode, toNode, weight);
                    break;
                case 3:
                    String prevFrom, prevTo;
                    do {
                        System.out.print("Dari: ");
                        prevFrom = scanner.nextLine().trim();
                        if (prevFrom.isEmpty()) {
                            System.out.println("Nama lokasi asal tidak boleh kosong!");
                        }
                    } while (prevFrom.isEmpty());

                    do {
                        System.out.print("Ke: ");
                        prevTo = scanner.nextLine().trim();
                        if (prevTo.isEmpty()) {
                            System.out.println("Nama lokasi tujuan tidak boleh kosong!");
                        }
                    } while (prevTo.isEmpty());

                    int newWeight = -1;
                    while (newWeight <= 0) {
                        System.out.print("Ubah Jarak (angka, >=0): ");
                        String newInput = scanner.nextLine();
                        try {
                            newWeight = Integer.parseInt(newInput);
                            if (newWeight <= 0) {
                                System.out.println("Jarak harus angka positif!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Input Jarak tidak valid!");
                        }
                    }
                    graph.updateEdge(prevFrom, prevTo, newWeight);
                    break;
                case 4:
                	System.out.println(" === Tampil Peta Logistik === ");
                    graph.printGraph();
                    break;
                case 5:
                    String startNode, endNode;
                    do {
                    	System.out.println(" === Tampil Jalur Terpendek === ");
                        System.out.print("Dari: ");
                        startNode = scanner.nextLine().trim();
                        if (startNode.isEmpty()) {
                            System.out.println("Nama lokasi asal tidak boleh kosong!");
                        }
                    } while (startNode.isEmpty());

                    do {
                        System.out.print("Ke: ");
                        endNode = scanner.nextLine().trim();
                        if (endNode.isEmpty()) {
                            System.out.println("Nama lokasi tujuan tidak boleh kosong!");
                        }
                    } while (endNode.isEmpty());

                    graph.shortestPath(startNode, endNode);
                    break;
                case 0:
                	System.out.println("Anda telah keluar");
                	return;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }
}
