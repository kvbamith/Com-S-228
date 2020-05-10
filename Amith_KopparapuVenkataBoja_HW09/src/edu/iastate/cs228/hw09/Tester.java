package edu.iastate.cs228.hw09;
/**
 * 
 * @author 
 * Amith Kopparapu Venkata Boja
 * 		A tester class made to test the preorder,postorder and levelorder itreative methods in the BinaryTree class
 *
 */
public class Tester {

	public static void main(String[] args) {
		BinaryTree<String> left1Tree = new BinaryTree<String>("C");
		BinaryTree<String> right1Tree = new BinaryTree<String>("D");
		BinaryTree<String> firstTree = new BinaryTree<String>();
		firstTree.setTree("B", left1Tree, right1Tree);
        
        BinaryTree<String> left2Tree = new BinaryTree<String>("F");
		BinaryTree<String> right2Tree = new BinaryTree<String>("G");
		BinaryTree<String> secondTree = new BinaryTree<String>();
		secondTree.setTree("E", left2Tree, right2Tree);
        
        BinaryTree<String> thirdTree = new BinaryTree<String>();
        thirdTree.setTree("A", firstTree, secondTree); 
        
        System.out.print("Expected: A B C D E F G \nOutput: ");
        thirdTree.iterativePreorderTraverse();
        System.out.println();
        System.out.print("Expected: C D B F G E A \nOutput: ");
        thirdTree.iterativePostorderTraverse();
        System.out.println();
        System.out.print("Expected: A B E C D F G  \nOutput: ");
        thirdTree.iterativeLevelorderTraverse();
        System.out.println();
	}

}
