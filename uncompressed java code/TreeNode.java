import java.util.*;

class TreeNode<AnyType> implements Comparable<AnyType> {

        private AnyType element;
        private TreeNode<AnyType> left;
        private TreeNode<AnyType> right;
        double distance;

        public TreeNode( ) {
                this( null, null, null, 0.0);
        }
        public TreeNode(AnyType theElement, TreeNode<AnyType> lt, TreeNode<AnyType> rt, double distance) {
                this.element = theElement;
                this.left = lt;
                this.right = rt;
                this.distance = distance;
                if (lt != null ) {
                        lt.distance = 1;
                }
                if (rt != null) {
                        rt.distance = 1;
                }
        }
        public AnyType getElement( ) {
                return element;
        }
        public TreeNode<AnyType> getLeft( ) {
                return left;
        }
        public TreeNode<AnyType> getRight( ) {
                return right;
        }
        public void setElement( AnyType x ) {
                element = x;
        }
        public void setLeft( TreeNode<AnyType> t ) {
                left = t;
        }
        public void setRight( TreeNode<AnyType> t ) {
                right = t;
        }
        public static <AnyType> int size( TreeNode<AnyType> t ) {
                if( t == null ) {
                        return 0;
                } else {
                        return 1 + size( t.left ) + size( t.right );
                }
        }
        public static <AnyType> int height( TreeNode<AnyType> t ) {
                if( t == null ) {
                        return -1;
                } else {
                        return 1 + Math.max( height( t.left ), height( t.right ));
                }
        }
        public TreeNode<AnyType> duplicate( ) {
                TreeNode<AnyType> root = new TreeNode<AnyType>(this.element, null, null, this.distance );
                if( left != null ) {
                        root.left = left.duplicate( ); // Duplicate; attach
                }
                if( right != null ) {
                        root.right = right.duplicate( ); // Duplicate; attach
                }
                return root; // Return resulting tree
        }
        // Print tree rooted at current node using preorder traversal.
        public void printPreOrder( ) {
                System.out.print( element ); // Node
                if( left != null ) {
                        left.printPreOrder( ); // Left
                }
                if( right != null ) {
                        right.printPreOrder( ); // Right
                }
        }
        // Print tree rooted at current node using postorder traversal.
        public void printPostOrder( ) {
                if( left != null ) {
                        left.printPostOrder( );
                }
                if( right != null ) {
                        right.printPostOrder( );
                }
                System.out.print( element );
        }
        // Print tree rooted at current node using inorder traversal.
        public void printInOrder( ) {
                if( left != null ) {
                        left.printInOrder( );
                }
                System.out.print( element );
                if( right != null ) {
                        right.printInOrder( );
                }
        }
        public void findClosest(TreeNode<AnyType> root, AnyType target) {
                BinaryHeap<TreeNode<AnyType>> heap = new BinaryHeap<>();
                root.distance = 0.0;
                insertChildren(root, heap);
                getClosest(root, target);
        }
        public void insertChildren(TreeNode<AnyType> q, BinaryHeap<TreeNode<AnyType>> heap) {
                heap.insert(q);
                if (q.getRight() != null) {
                        TreeNode<AnyType> right = q.getRight();
                        right.distance += q.distance;
                        insertChildren(q.getRight(), heap);
                }
                if (q.getLeft() != null) {
                        TreeNode<AnyType> left = q.getLeft();
                        left.distance += q.distance;
                        insertChildren(q.getLeft(), heap);
                }
        }
        public void getClosest(TreeNode<AnyType> root, AnyType target) {
                returnAllAsMap(root);
                for (Map.Entry<AnyType, Double> el : hm.entrySet()) {
                        if (el.getKey().equals(target)) {
                                System.out.println("found \"" + target + "\"" + " at distance " + el.getValue() + ".");
                                break;
                        }
                }
        }
        public HashMap<AnyType, Double> hm = new HashMap<>();
        public void returnAllAsMap(TreeNode<AnyType> root) {
                if (hm.containsKey(root.element)) {
                        if (hm.get(root.element) > root.distance) {
                                hm.put(root.element, root.distance);
                        }
                } else {
                        hm.put(root.element, root.distance);
                        if(root.left != null) {
                                returnAllAsMap(root.left);
                        }
                        if(root.right != null) {
                                returnAllAsMap(root.right);
                        }
                }
        }
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Object o) {
                TreeNode<AnyType> at = (TreeNode<AnyType>) o;
                return Double.compare(at.distance, this.distance);
        }
}
