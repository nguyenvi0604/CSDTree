/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LEGION
 */
public class BinaryTreeService {

    Node root;

    public BinaryTreeService() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
        System.out.println("Clear the tree!");
    }

    public Node search(Node p, int key) {
        if (p == null) {
            System.out.println("Tree null!");
            return null;
        }
        if (p.info == key) {
            return p;
        } else if (p.info > key) {
            return search(p.left, key);
        } else {
            return search(p.right, key);
        }
    }

    public void insert(int x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info == x) {
                System.out.println("The key " + x + " already exists, no insertion");
                return;
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x < f.info) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }

    void visit(Node p) {
        System.out.print(p.info + "  ");
    }

    public void breadth(Node p) {
        if (p == null) {
            System.out.println("Tree null");
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            visit(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

//    int count(Node p) {
//        if (p == null) {
//            System.out.println("Tree null");
//            return 0;
//        } else {
//            int lDepth = count(p.left);//compute the depth of each subtree
//            int rDepth = count(p.right);
//            if (lDepth > rDepth) {
//                return (lDepth + 1);//use the larger one
//            } else {
//                return (rDepth + 1);
//            }
//        }
//    }'
    int count(Node p) {
        int count = 1;
        if (root == null) {
            System.out.println("Tree null");
            return 0;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left != null) {
                q.enqueue(r.left);
                count++;
            }
            if (r.right != null) {
                q.enqueue(r.right);
                count++;
            }
        }
        return count;
    }

    public void delete(int x) {
        Node p = search(root, x);
        if (p == null) {
            System.out.println("Key " + x + " does not exists, deletion failed");
            return;
        }
        //find f is father of p
        Node f = null, q = root;
        while (q != p) {
            f = q;
            if (q.info > p.info) {
                q = q.left;
            } else {
                q = q.right;
            }
        }
        //1.p has no child
        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
            } else if (f.left == p) {
                f.left = null;
            } else {
                f.right = null;
            }
        } //2.p has left child only
        else if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        } //3.p has right child only
        else if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else if (f.left == p) {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        } //4.p has both child
        else if (p.left != null && p.right != null) {
            //tim q la node lon nhat ben con trai cua p -> q la con phai nhat
            //cua con trai cua p
            q = p.left;
            f = null;
            while (q.right != null) {
                f = q;
                q = q.right;
            }
            p.info = q.info;
            //delete q
            if (f == null) {
                p.left = q.left;
            } else {
                f.right = q.left;
            }
        }
    }

    public Node min() {
        if (root == null) {
            System.out.println("Tree null");
            return null;
        }
        // min la nam ben trai, neu ban trai xet ben phai
        Node f = null;
        if ((root.left != null && root.right == null) || (root.left != null && root.right != null)) {
            f = search(root, Math.min(root.left.info, root.left.left.info));
            return f;
        }
        if (root.left == null && root.right != null) {
            f = root;
            return f;
        }
        return f;
    }

    public Node max() {
        if (root == null) {
            System.out.println("Tree null");
            return null;
        }
        Node f = null;
        if ((root.left != null && root.right == null) || (root.left == null && root.right == null)) {
            return root;
        }
        if ((root.left == null && root.right != null) || (root.left != null && root.right != null)) {
            f = search(root, Math.max(root.right.info, root.right.right.info));
            return f;
        }
        return f;
    }

    public int sumTree() {
        int sum = 0;
        if (root == null) {
            System.out.println("Tree null");
            return 0;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node r;
        sum = sum + root.info;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.left != null) {
                q.enqueue(r.left);
                sum = sum + r.left.info;
            }
            if (r.right != null) {
                q.enqueue(r.right);
                sum = sum + r.right.info;
            }
        }
        return sum;
    }
    public int averageTree(){
        if(root == null){
            System.out.println("Tree null");
            return 0;
        }
        return sumTree()/count(root);
        
    }

    void insertMany(int[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }
}
