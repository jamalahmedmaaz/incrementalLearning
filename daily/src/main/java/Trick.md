1. Prefix Sum + Index Deque (Advanced Sliding window).
2. Is queen place is valid.

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
            ListNode p = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return p;
        }**
         
6. Validate Parenthesis Balancing

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