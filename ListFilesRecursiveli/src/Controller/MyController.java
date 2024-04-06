package Controller;

import View.MyView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyController {
    // Yêu cầu: Sử dụng Stream() cho Collection (ArrayList, List ...)
    public static List<String> loadFromFile(String fileName) {
        List<String> content = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            content = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // Yêu cầu: Sử dụng File để lưu và load lại dữ liệu
    public static void saveToFile(String fileName, List<String> content) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            content.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Yêu cầu: Sử dụng lambdas expresstion cho xử lý sự kiện (->)
    public static void openFile(JFrame frame, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            List<String> content = loadFromFile(selectedFile.getAbsolutePath());
            if (content != null) {
                ((MyView) frame).updateTextArea(content);
            }
        }
    }

    // Yêu cầu: Sử dụng lambdas expresstion cho xử lý sự kiện (->)
    public static void saveFile(JFrame frame, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            String content = textArea.getText();
            try {
                saveToFile(fileName, List.of(content));
                JOptionPane.showMessageDialog(frame, "Saved successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Yêu cầu: Sử dụng đệ quy để duyệt thư mục
    public static void browseDirectory(JFrame frame, JTree fileTree) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            ((MyView) frame).updateFileTree(selectedDirectory);
        }
    }

    // Yêu cầu: Sử dụng đệ quy để duyệt thư mục
    public static void populateTree(File directory, DefaultMutableTreeNode parentNode) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
                parentNode.add(node);
                if (file.isDirectory()) {
                    populateTree(file, node);
                }
            }
        }
    }
}
