/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
 
public class Solution {
  static class Cell {
    TreeNode node;
    int level;
    
    public Cell(TreeNode node, int level) {
      this.node = node;
      this.level = level;
    }
  }
  public List<Integer> verticalOrder(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    // corner case
    if (root == null) return res;
    
    Deque<Cell> stack = new LinkedList<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    // initialize
    stack.offerLast(new Cell(root, 0));
    // maintain min level to help convert map to list result
    int min = 0;
    
    // level traversal
    while (!stack.isEmpty()) {
      Cell cur = stack.pollFirst();
      min = Math.min(min, cur.level);
      List<Integer> list = map.get(cur.level);
      if (list == null) {
        List<Integer> l = new ArrayList<>();
        l.add(cur.node.key);
        map.put(cur.level, l);
      } else {
        list.add(cur.node.key);
      }
      if (cur.node.left != null) {
        stack.offerLast(new Cell(cur.node.left, cur.level - 1));
      }
      if (cur.node.right != null) {
        stack.offerLast(new Cell(cur.node.right, cur.level + 1));
      }
    }
    return convert(map, min);
  }
  
 // here we record a min value to keep the keys in map in order
 // remember, we can just use a treemap to record the order
  private List<Integer> convert(Map<Integer, List<Integer>> map, int min) {
    List<Integer> res = new ArrayList<>();
    for (int i = min; i < min + map.size(); i++) {
      List<Integer> list = map.get(i);
      for (int num : list) {
        res.add(num);
      }
    }
    return res;
  }
}

