/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LEGION
 */
public class Main {

    public static void main(String[] args) {
        int[] a = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        BinaryTreeService t = new BinaryTreeService();
        t.insertMany(a);
        System.out.println("1. Test breadth-first traversal:");
        t.breadth(t.root);
        System.out.println();

        System.out.println("2. Test pre-order traversal:");
        t.preOrder(t.root);
        System.out.println();

        System.out.println("3. Test in-order traversal:");
        t.inOrder(t.root);
        System.out.println();

        System.out.println("4. Test post-order traversal:");
        t.postOrder(t.root);
        System.out.println();
        
        System.out.println("5. Test Count: "+t.count(t.root));
        System.out.print("6. Test delete: ");
        t.delete(8);
        t.breadth(t.root);
        
        System.out.println("\n7. Test node min: "+t.min().info);
        System.out.println("8. Test node max: "+t.max().info);
        System.out.println("9. Test sum: "+t.sumTree());
        System.out.println("10. Test average: "+t.averageTree());
    }
}
