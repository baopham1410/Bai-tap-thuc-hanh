package View;

import Controller.MyController;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class MyView extends JFrame {
    private JTextArea textArea;
    private JTree fileTree;


    public void updateTextArea(List<String> content) {
        StringBuilder sb = new StringBuilder();
        for (String line : content) {
            sb.append(line).append("\n");
        }
        textArea.setText(sb.toString());
    }
    public MyView() {
        super("Bai Tap Thuc Hanh");
        textArea = new JTextArea();
        JScrollPane textScrollPane = new JScrollPane(textArea);
        fileTree = new JTree();
        fileTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane fileScrollPane = new JScrollPane(fileTree);
        JButton openButton = new JButton("Mở");
        openButton.addActionListener(e -> MyController.openFile(this, textArea));
        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(e -> MyController.saveFile(this, textArea));
        JButton browseButton = new JButton("Duyệt");
        browseButton.addActionListener(e -> MyController.browseDirectory(this, fileTree));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(browseButton);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(fileScrollPane, BorderLayout.WEST);
        contentPane.add(textScrollPane, BorderLayout.CENTER);
        // Set background color
        contentPane.setBackground(Color.WHITE);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
    }

    public void updateFileTree(File directory) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(directory);
        MyController.populateTree(directory, rootNode);
        fileTree.setModel(new DefaultTreeModel(rootNode));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            MyView textView = new MyView();
            textView.setVisible(true);
        });
    }

}
