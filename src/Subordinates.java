/**
 * Subordinates
 * @author Hector Murcia Forero
 * source url: https://cses.fi/problemset/task/1674
 * 
 * Given the structure of a company, your task is to calculate for each employee
 * the number of their subordinates.
 * Input
 *   The first input line has an integer n: the number of employees. The
 *   employees are numbered 1,2,\dots,n, and employee 1 is the general director
 *   of the company.
 *   After this, there are n-1 integers: for each employee 2,3,\dots,n their
 *   direct boss in the company.
 * Output
 *   Print n integers: for each employee 1,2,...,n the number of their
 *   subordinates.
 * Constraints
 *   1 <= n <= 2 · 10^5
 * Example
 *   Input:
 *     5
 *     1 1 2 3
 *   Output:
 *     4 1 1 0 0
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Node structure for tree
class Node {
    long data;
    List<Node> children;

    Node(long x) {
        data = x;
        children = new ArrayList<>();
    }
    Node get(long p) {
        if (p == data)
            return this;
        for (Node node : children)
            return node.get(p);
        return null;
    }
}

public class Subordinates {
    // Function to add a child to a node
    static void addChild(Node parent, Node child) {
        if (parent == null) return;
        parent.children.add(child);
    }

    // Function to print parents of each node
    static void printParents(Node node, Node parent) {
        if (parent == null)
            System.out.println(node.data + " -> NULL");
        else
            System.out.println(node.data + " -> " + parent.data);

        for (Node child : node.children)
            printParents(child, node);
    }

    // Function to print children of each node
    static void printChildren(Node node) {
        System.out.print(node.data + " -> ");
        for (Node child : node.children)
            System.out.print(child.data + " ");
        System.out.println();

        for (Node child : node.children)
            printChildren(child);
    }

    // Function to print leaf nodes
    static void printLeafNodes(Node node) {
        if (node.children.isEmpty()) {
            System.out.print(node.data + " ");
            return;
        }
        for (Node child : node.children)
            printLeafNodes(child);
    }

    // Function to print degrees of each node 
    static void printDegrees(Node node, Node parent) {
        int degree = node.children.size();
        if (parent != null)
            degree++;
        System.out.println(node.data + " -> " + degree);

        for (Node child : node.children)
            printDegrees(child, node);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // The first input line has an integer n: the number of employees
        long n = sc.nextLong();

        // Relacionado padre - hijo
        Node root = new Node(1);
        for (long i = 2; i <= n; i++) {
            Node node = new Node(i);
            long idParent = sc.nextLong();
            addChild(root.get(idParent), node); // Construyendo el árbol
        }

        System.out.println("Parents of each node:");
        printParents(root, null);

        System.out.println("Children of each node:");
        printChildren(root);

        System.out.print("Leaf nodes: ");
        printLeafNodes(root);
        System.out.println();

        System.out.println("Degrees of nodes:");
        printDegrees(root, null);
    }
}