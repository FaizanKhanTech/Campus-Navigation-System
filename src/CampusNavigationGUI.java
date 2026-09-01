package gui;

import javax.swing.*;
import java.awt.*;

import algorithms.BFS;
import algorithms.DFS;
import algorithms.Dijkstra;
import graph.Graph;
import model.Location;
import structures.BinarySearchTree;
import structures.LocationDirectory;
import structures.LocationQueue;
import structures.LocationStack;

public class CampusNavigationGUI extends JFrame {

    private Graph graph;

    private Location cs;
    private Location library;
    private Location cafeteria;
    private Location admin;
    private Location sports;

    private JComboBox<String> startBox;
    private JComboBox<String> endBox;

    private JTextArea output;

    public CampusNavigationGUI() {

        createGraph();

        setTitle("Campus Navigation");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        JLabel title = new JLabel(
                "Campus Navigation System",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel topPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        JPanel locations = new JPanel(
                new GridLayout(2, 2, 8, 8)
        );

        startBox = new JComboBox<>(new String[] {
                "Computer Science Department",
                "Central Library",
                "Cafeteria",
                "Admin Block",
                "Sports Complex"
        });

        endBox = new JComboBox<>(new String[] {
                "Computer Science Department",
                "Central Library",
                "Cafeteria",
                "Admin Block",
                "Sports Complex"
        });

        locations.add(
                new JLabel("Start Location:")
        );

        locations.add(startBox);

        locations.add(
                new JLabel("Destination:")
        );

        locations.add(endBox);

        topPanel.add(
                locations,
                BorderLayout.NORTH
        );

        output = new JTextArea();

        output.setEditable(false);

        output.setFont(
                new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollPane =
                new JScrollPane(output);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Output"
                )
        );

        topPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                topPanel,
                BorderLayout.CENTER
        );

        JPanel buttons = new JPanel(
                new GridLayout(3, 3, 8, 8)
        );

        JButton bfs = new JButton("BFS");
        JButton dfs = new JButton("DFS");
        JButton shortest = new JButton("Shortest Path");
        JButton graphButton = new JButton("Show Graph");
        JButton bst = new JButton("Binary Search Tree");
        JButton stack = new JButton("Location History");
        JButton queue = new JButton("Location Queue");
        JButton directory = new JButton("Location Directory");
        JButton clear = new JButton("Clear");

        buttons.add(bfs);
        buttons.add(dfs);
        buttons.add(shortest);
        buttons.add(graphButton);
        buttons.add(bst);
        buttons.add(stack);
        buttons.add(queue);
        buttons.add(directory);
        buttons.add(clear);

        mainPanel.add(
                buttons,
                BorderLayout.SOUTH
        );

        bfs.addActionListener(e -> runBFS());
        dfs.addActionListener(e -> runDFS());
        shortest.addActionListener(e -> runDijkstra());
        graphButton.addActionListener(e -> showGraph());
        bst.addActionListener(e -> showBST());
        stack.addActionListener(e -> showStack());
        queue.addActionListener(e -> showQueue());
        directory.addActionListener(e -> showDirectory());

        clear.addActionListener(
                e -> output.setText("")
        );

        add(mainPanel);
    }

    private void createGraph() {

        graph = new Graph();

        cs = new Location(
                1,
                "Computer Science Department"
        );

        library = new Location(
                2,
                "Central Library"
        );

        cafeteria = new Location(
                3,
                "Cafeteria"
        );

        admin = new Location(
                4,
                "Admin Block"
        );

        sports = new Location(
                5,
                "Sports Complex"
        );

        graph.addLocation(cs);
        graph.addLocation(library);
        graph.addLocation(cafeteria);
        graph.addLocation(admin);
        graph.addLocation(sports);

        graph.addEdge(cs, cafeteria, 200);
        graph.addEdge(cafeteria, cs, 200);

        graph.addEdge(cafeteria, admin, 150);
        graph.addEdge(admin, cafeteria, 150);

        graph.addEdge(admin, library, 300);
        graph.addEdge(library, admin, 300);

        graph.addEdge(cafeteria, sports, 250);
        graph.addEdge(sports, cafeteria, 250);

        graph.addEdge(cs, admin, 500);
        graph.addEdge(admin, cs, 500);
    }

    private Location getLocation(String name) {

        if (name.equals("Computer Science Department"))
            return cs;

        if (name.equals("Central Library"))
            return library;

        if (name.equals("Cafeteria"))
            return cafeteria;

        if (name.equals("Admin Block"))
            return admin;

        if (name.equals("Sports Complex"))
            return sports;

        return null;
    }

    private void runBFS() {

        Location start =
                getLocation(
                        (String) startBox.getSelectedItem()
                );

        output.setText("");

        output.append("BFS Traversal\n");
        output.append("----------------------\n");
        output.append(
                "Starting from: "
                + start.getName()
                + "\n\n"
        );

        output.append(
                BFS.traverse(graph, start)
        );
    }

    private void runDFS() {

        Location start =
                getLocation(
                        (String) startBox.getSelectedItem()
                );

        output.setText("");

        output.append("DFS Traversal\n");
        output.append("----------------------\n");
        output.append(
                "Starting from: "
                + start.getName()
                + "\n\n"
        );

        output.append(
                DFS.traverse(graph, start)
        );
    }

    private void runDijkstra() {

        Location start =
                getLocation(
                        (String) startBox.getSelectedItem()
                );

        Location end =
                getLocation(
                        (String) endBox.getSelectedItem()
                );

        output.setText("");

        output.append("Shortest Path\n");
        output.append("----------------------\n");
        output.append(
                "From: "
                + start.getName()
                + "\n"
        );

        output.append(
                "To: "
                + end.getName()
                + "\n\n"
        );

        output.append(
                Dijkstra.findShortestPath(
                        graph,
                        start,
                        end
                )
        );
    }

    private void showGraph() {

        output.setText("");

        output.append("Campus Graph\n");
        output.append("----------------------\n\n");

        output.append(
                graph.getGraphString()
        );
    }

    private void showBST() {

        output.setText("");

        output.append("Binary Search Tree\n");
        output.append("----------------------\n\n");

        BinarySearchTree bst =
                new BinarySearchTree();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        output.append(
                "Values inserted:\n"
        );

        output.append(
                "50 30 70 20 40 60 80\n\n"
        );

        output.append(
                "Search 40: "
                + bst.search(40)
                + "\n"
        );

        output.append(
                "Search 90: "
                + bst.search(90)
                + "\n"
        );

        output.append(
                "\nInorder traversal is demonstrated "
                + "in the console by the current BST class."
        );

        bst.inorder();
    }

    private void showStack() {

        output.setText("");

        output.append(
                "Location History - Stack\n"
        );

        output.append(
                "----------------------\n\n"
        );

        LocationStack history =
                new LocationStack();

        history.push(cs);
        history.push(cafeteria);
        history.push(admin);
        history.push(library);

        output.append(
                "Visited locations:\n"
        );

        output.append(
                "Computer Science Department\n"
        );

        output.append("Cafeteria\n");
        output.append("Admin Block\n");
        output.append("Central Library\n\n");

        output.append(
                "Current location: "
                + history.peek().getName()
                + "\n"
        );

        Location removed =
                history.pop();

        output.append(
                "Removed location: "
                + removed.getName()
                + "\n"
        );

        output.append(
                "Current location after pop: "
                + history.peek().getName()
                + "\n"
        );
    }

    private void showQueue() {

        output.setText("");

        output.append(
                "Location Queue\n"
        );

        output.append(
                "----------------------\n\n"
        );

        LocationQueue queue =
                new LocationQueue();

        queue.enqueue(cs);
        queue.enqueue(library);
        queue.enqueue(cafeteria);
        queue.enqueue(admin);

        output.append(
                "Queue order:\n"
        );

        output.append(
                "Computer Science Department\n"
        );

        output.append("Central Library\n");
        output.append("Cafeteria\n");
        output.append("Admin Block\n\n");

        output.append(
                "Next location: "
                + queue.peek().getName()
                + "\n"
        );

        Location removed =
                queue.dequeue();

        output.append(
                "Removed location: "
                + removed.getName()
                + "\n"
        );

        output.append(
                "Next location after dequeue: "
                + queue.peek().getName()
                + "\n"
        );
    }

    private void showDirectory() {

        output.setText("");

        output.append(
                "Location Directory\n"
        );

        output.append(
                "----------------------\n\n"
        );

        LocationDirectory directory =
                new LocationDirectory();

        directory.addLocation(cs);
        directory.addLocation(library);
        directory.addLocation(cafeteria);
        directory.addLocation(admin);
        directory.addLocation(sports);

        output.append(
                "Total locations: "
                + directory.size()
                + "\n\n"
        );

        Location found =
                directory.findLocation(3);

        if (found != null) {

            output.append(
                    "Search ID 3: "
                    + found.getName()
                    + "\n"
            );

        } else {

            output.append(
                    "Location not found.\n"
            );
        }
    }

    public static void main(String[] args) {

        CampusNavigationGUI window =
                new CampusNavigationGUI();

        window.setVisible(true);
    }
}
