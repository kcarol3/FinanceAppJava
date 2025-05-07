package com.example.FinanceApp.flyweight.transactionIcon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Tydzien 3, Wzorzec Flyweight, tworzenie obiektow ikon do transakcji
public class TransactionIcon implements TransactionIconInterface {
    private final BufferedImage image;

    public TransactionIcon(String imagePath) throws IOException {
        this.image = ImageIO.read(new File(imagePath));
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
// Koniec tydzien 3, wzorzec flyweight 1
