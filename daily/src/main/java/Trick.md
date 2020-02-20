1. Prefix Sum + Index Deque (Advanced Sliding window).
2. Is queen place valid.

        **private boolean validate(char[][] board, int x, int y) {
              for(int i = 0; i < board.length; i++) {
                  for(int j = 0; j < y; j++) {
                      if(board[i][j] == 'Q' && 
                      (x + j == y + i || x + y == i + j || x == i))
                          return false;
                  }
              }
              return true;
          }**

3. Breaking a string with a queue (skipping one character).

        **while(!q.isEmpty()){
            String current = q.poll();
            if(balanced(current)){
                result.add(current);
                found = true;
            }
            if(found) continue;
            for(int i = 0 ; i < current.length(); i++){
                if(current.charAt(i) != '(' && current.charAt(i) != ')') continue;
                String tmp = current.substring(0,i) + current.substring(i+1);
                if(!visited.contains(tmp)){
                    q.add(tmp);
                    visited.add(tmp);
                }
            }
        }**

4. Reversing pairs of elements RECURSION.

        **public ListNode swapPairs(ListNode head) {
            if(head == null || head.next == null)
                return head;
            ListNode next = head.next;
            head.next = swapPairs(head.next.next);
            next.next = head;
            return next;
        }**

5. Reverse complete Linked List RECURSION.

        **public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode lastNode = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return lastNode;
        }**

6. Search in Binary Search Tree RECURSION.

        **public TreeNode searchBST(TreeNode root, int val) {
            if(root == null)
                return root;
            if(root.val > val){
                return searchBST(root.left, val);
            }else if(root.val < val){
                return searchBST(root.right, val);
            }
            return root;
        }**
        
        U Turn Operation on LinledList. (making train compartments
        reversely linked)
        
        **head.next.next = head;
        head.next = head;**
        

7. Pascals Triangle return a row of pascal triangle RECURSION

        **f(row,col) = f(row-1,col-1) + f(row-1,col);**
        
    
        **private int getNum(int row, int col) {
            if (row == 0 || col == 0 || row == col) {
                return 1;
            }
            String key = row + "_" + col;
            if(dp.containsKey(key)){
                return dp.get(key);
            }
            int val = getNum(row - 1, col - 1) + getNum(row - 1, col);
            dp.put(key,val);
            return val;
        }
    
        public List<Integer> getRow(int rowIndex) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i <= rowIndex; i++) {
                ans.add(getNum(rowIndex, i));
            }
            return ans;
        }**

8. Recursive algorithm Time complexity calculation

    Given a recursion algorithm, its time complexity 
    O(T) is typically the product of the number of recursion 
    invocations (denoted as R) and the time complexity of calculation 
    (denoted as O(s)) that incurs along with each recursion 
    call:
    
    O(T) = R * O(s);
    
    
9. BitCount - Kth Symbol in Grammar. Given N and K, find the Kth (K-1), value
    of string.

        **class Solution {
            public int kthGrammar(int N, int K) {
                return Integer.bitCount(K - 1) % 2;
            }
        }**

10. When a 2D matrix is given with sorted rows and sorted columns. You can think
    as you are creating a 1D matrix (intuition you will see elements are 
    sorted). Now what is the low and high (to do Binary Search).
    
    ```low = 0; high = r * c - 1;
    mid = low + (high - low) /2;
    
    midEle = A[mid / c ][ mid % c ];```

11. Finding Contiguous of anything (example: number of Contiguous elements 
    equal K).
    
    
        map.put(0,1);               //Because when you reach sum-k == 0.
        for(int ele : nums){
            sum = sum + ele;
            if(map.containsKey(sum-k)){
                count = count + map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
      

12. Validate Parenthesis Balancing

        **public boolean balanced(String s){
            int count = 0;
            for(int i = 0 ; i < s.length(); i++){
                char c = s.charAt(i);
                if(c == '(') count++;
                if(c == ')') count--;
                if(count < 0) return false;
            }
            return count == 0;
        }**        

13. Binary Tree Iterator.
    1. Use Stack to add the all left most element in stack (O(log(N)).
    2. hasNext => !stack.isEmpty()
    3. next() => 
    
       **public int next() {
           TreeNode current = stack.pop();
           pushAll(current.right);
           return current.val;
       }**

14. Vertical Order of Binary Tree
    
        **TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> nodes;
        position, height, (elements....)
        -1, 0, (1,2)**
           
15. LCS Formula.

        **public TreeNode rec(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return root;
            if (root == p) return root;
            if (root == q) return root;
            TreeNode left = rec(root.left, p, q);
            TreeNode right = rec(root.right, p, q);
            if (left != null && right != null) return root;
            return left != null ? left : right;
        }**

16. Binary Tree Upside down

        **TreeNode parent = null;
        public TreeNode rec(TreeNode root){
            if(root == null) return null;
            TreeNode right = rec(root.right);
            TreeNode left  = rec(root.left);
    
            root.left = parent;
            
            parent = root;
            return root;
        }**

17.