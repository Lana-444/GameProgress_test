package org.example;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameProgressTest {

    @Test
    void saveGame() {
        // given:
        GameProgress game = new GameProgress(10,10,10,10);
        String filePath = "C://Java//Games//savegames//testFile.txt";

        // when:
        game.saveGame(filePath);

        // then:
        File file = new File(filePath);
        assertTrue(file.exists());

    }

    @Test
    void zipFiles() {

        String sourceFilePath = "C://Java//Games//savegames//testFile.txt";
        String zipFilePath = "C://Java//Games//savegames//testFile.zip";

        // given:
        try {
            File file = new File(sourceFilePath);
            if (file.createNewFile()) {
                System.out.println("Test file created successfully.");
            }
        } catch (IOException e) {
            System.err.println("Error creating test file: " + e.getMessage());
        }

        // when:
        GameProgress.zipFiles(sourceFilePath, zipFilePath);

        // then:
        File zipFile = new File(zipFilePath);
        assertTrue(zipFile.exists());

    }


    @Test
    void delFiles() {
        // given:
        String filePath = "C://Java//Games//savegames//testFile.txt";

        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("Test file created successfully.");
            }
        } catch (IOException e) {
            System.err.println("Error creating test file: " + e.getMessage());
        }


        // when:
        boolean result = GameProgress.delFiles(filePath);

        // then:
        assertTrue(result);


    }
}