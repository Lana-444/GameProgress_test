package org.example;

import java.io.*;
import java.io.Serializable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {

    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        String filePath = "C://Java//Games//savegames//gameProgress.txt";
        String zipFilePath = "C://Java//Games//savegames//gameProgress.zip";

        GameProgress game1 = new GameProgress(1, 1, 1, 1);
        GameProgress game2 = new GameProgress(2, 2, 2, 2);
        GameProgress game3 = new GameProgress(3, 3, 3, 3);

        game1.saveGame("C://Java//Games//savegames//gameProgress.txt");
        game2.saveGame("C://Java//Games//savegames//gameProgress.txt");
        game3.saveGame("C://Java//Games//savegames//gameProgress.txt");
        zipFiles(filePath, zipFilePath);
        delFiles(filePath);

    }

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public static void saveGame(String s) {

        String filePath = "C://Java//Games//savegames//gameProgress.txt";

        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(filePath);

            System.out.println("Объект успешно сериализован и записан в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при записи сериализованных данных: " + e.getMessage());
        }

    }

    public static void zipFiles(String filePath, String zipFilePath) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            zos.putNextEntry(new ZipEntry("packed" + "C://Java//Games//savegames//gameProgress.txt"));
            byte[] buffer = new byte[bis.available()];
            bis.read(buffer);
            zos.write(buffer);
            zos.closeEntry();
            openProgress();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean delFiles(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Файл " + file + " удален");
                return true;
            } else {
                System.err.println("Невозможно удалить файл");
                return false;
            }
        } else {
            System.err.println("Файл не существует.");
            return false;
        }
    }


    public static void openProgress() {
        try (FileInputStream fis = new FileInputStream("C://Java//Games//savegames//gameProgress.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o = ois.readObject();
            if (o instanceof GameProgress gameProgress) {
                System.out.println(gameProgress);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}