import java.util.ArrayDeque;
import java.util.Deque;

class Stack{
    class IndexedChar{
        private char c;
        private int i;

        public int getIndex(){
            return this.i;
        }

        public IndexedChar(char c, int i) {
            this.c = c;
            this.i = i;
        }
    }
    private Deque<IndexedChar> stack = new ArrayDeque<IndexedChar>();
    public void push(Character c, int i) {
        stack.addFirst(new IndexedChar(c, i));
    }
    public IndexedChar pop() throws StackUnderflowException{
        if(stack.isEmpty()) throw new StackUnderflowException();
        return stack.removeFirst();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
