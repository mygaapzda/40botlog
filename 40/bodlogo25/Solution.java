boolean checkBST(Node root) {
    return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

boolean isBST(Node node, int min, int max) {
    if (node == null) return true;
    
 
    if (node.data <= min || node.data >= max) return false;

    return isBST(node.left, min, node.data) &&
           isBST(node.right, node.data, max);
}
