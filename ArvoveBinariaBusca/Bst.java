// A classe BST (Binary Search Tree) gerencia a árvore.
public class BST {
    public No root; 

    //A árvore começa com a raiz vazia (null).
    public BST(){
        this.root = null;
    }


    //Esse método é chamado para inserir um novo valor na árvore.
    //Ele chama o método privado put(No no, Integer key), passando a raiz (this.root) e a chave (key) a ser inserida.
    public void put(Integer key){
        this.root = put(this.root, key);
    }

    //no -> O nó atual onde estamos tentando inserir o novo valor.
    //key -> O valor que queremos inserir.
    private No put(No no, Integer key){
        
        //no == null → Significa que chegamos a um espaço vazio na árvore onde o valor pode ser inserido.
        //novoNo → Criamos um novo nó com o valor key e retornamos ele.
        //Quando encontramos um local vazio (null), criamos um novo nó e o retornamos para ser ligado ao pai.
        if(no == null){
            No novoNo = new No(key);
            return novoNo;
        }
        
        //key → O valor que queremos inserir.
        //no.key → O valor do nó atual, no caso o no que estamos comparado com a key o valor novo a ser inserido
        //Se key < no.key, significa que devemos inserir na esquerda.
        //Se key > no.key, devemos inserir na direita.
        //A chamada put(no.left, key) ou put(no.right, key) continua a recursão até encontrar um espaço vazio (null).
        if(key < no.key){
            no.left = put(no.left, key);
        }else if(key > no.key){
            no.right = put(no.right, key);
        }

        return no;
    }

    public int depth(){
        return depth(this.root);
    }

    private int depth(No no){
        if(no == null){
            return -1;
        }

        return 1 + Math.max(depth(no.left), depth(no.right));
    }

    public Integer get(Integer key){
        return get(this.root, key);
    }

    private Integer get(No no, Integer key){
        if(key == null){
            throw new IllegalArgumentException("chave nula");
        }

        if(no == null){
            return null;
        }

        if(key > no.key){
           return get(no.right, key);
        }else if(key < no.key){
            return get(no.left, key);
        }else{
            return no.key;
        }
    }

    public void delete(Integer key){
        this.root = delete(this.root, key);
    }

    private No delete(No no, Integer key){
        if(no == null){
            return null;
        }

        if(key > no.key){
            no.right = delete(no.right, key);
        }else if(key < no.key){
            no.left = delete(no.left, key);
        }else{
            if(no.right == null){
                return no.left;
            }
            if(no.left == null){
                return no.right;
            }

            No y = no;
            no = min(y.right);
            no.right = deleteMin(y.right);
            no.left = y.left;
        }   
        return no;
    }

    private No min(No no){
        if(no.left == null){
            return no;
        }

        return min(no.left);
    }

    private No deleteMin(No no){
        if(no.left == null){
            return no.right;
        }

        no.left = deleteMin(no.left);
        return no;
    }

}