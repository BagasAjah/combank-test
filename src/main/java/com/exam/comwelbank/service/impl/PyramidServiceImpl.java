package com.exam.comwelbank.service.impl;

import com.exam.comwelbank.service.PyramidService;
import org.springframework.stereotype.Service;

@Service
public class PyramidServiceImpl implements PyramidService {

    @Override
    public void printPyramid(int totalRows) {
        final String SYMBOL = "#";
        printPyramid(totalRows, SYMBOL);
    }

    private void printPyramid(int rows, String symbol) {
        int symbolRow = 0;
        final String SPACE_STRING = " ";
        System.out.println("Print Pyramid with total rows: " + rows);
        for (int iterate = 1; iterate <= rows; ++iterate, symbolRow = 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int spaceChar = 1; spaceChar <= rows - iterate; ++spaceChar) {
                stringBuilder.append(SPACE_STRING);
            }
            while (symbolRow != iterate) {
                stringBuilder.append(symbol);
                ++symbolRow;
            }
            System.out.println(stringBuilder.toString());
        }
    }
}
