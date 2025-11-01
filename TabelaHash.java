// Classe Entry para armazenar chave e valor
class Entry {
    int chave;
    String valor;
    boolean deletado;
    
    public Entry(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
        this.deletado = false;
    }
}

// Tabela Hash com Enderecamento Aberto - Dispersao Dupla
public class TabelaHash {
    private Entry[] tabela;
    private int m;  // tamanho da tabela
    private int n;  // numero de elementos
    
    // Construtor
    public TabelaHash(int tamanho) {
        this.m = tamanho;
        this.tabela = new Entry[m];
        this.n = 0;
    }
    
    // Primeira funcao hash - metodo da divisao
    private int h1(int chave) {
        return chave % m;
    }
    
    // Segunda funcao hash
    private int h2(int chave) {
        return 7 - (chave % 7);  // usando 7 como constante
    }
    
    // Funcao de dispersao dupla
    private int hash(int chave, int i) {
        return (h1(chave) + i * h2(chave)) % m;
    }
    
    // Inserir elemento
    public void inserir(int chave, String valor) {
        if (n >= m) {
            System.out.println("Tabela cheia!");
            return;
        }
        
        int i = 0;
        int posicao;
        
        while (i < m) {
            posicao = hash(chave, i);
            
            // Se posicao vazia ou deletada, insere
            if (tabela[posicao] == null || tabela[posicao].deletado) {
                tabela[posicao] = new Entry(chave, valor);
                n++;
                System.out.println("Chave " + chave + " inserida na posicao " + posicao);
                return;
            }
            
            // Se chave ja existe, atualiza
            if (tabela[posicao].chave == chave && !tabela[posicao].deletado) {
                tabela[posicao].valor = valor;
                System.out.println("Chave " + chave + " atualizada");
                return;
            }
            
            i++;
        }
        
        System.out.println("Nao foi possivel inserir");
    }
    
    // Buscar elemento
    public String buscar(int chave) {
        int i = 0;
        int posicao;
        
        while (i < m) {
            posicao = hash(chave, i);
            
            // Se posicao vazia, elemento nao existe
            if (tabela[posicao] == null) {
                System.out.println("Chave " + chave + " nao encontrada");
                return null;
            }
            
            // Se encontrou a chave e nao esta deletada
            if (tabela[posicao].chave == chave && !tabela[posicao].deletado) {
                System.out.println("Chave " + chave + " encontrada na posicao " + posicao);
                return tabela[posicao].valor;
            }
            
            i++;
        }
        
        System.out.println("Chave " + chave + " nao encontrada");
        return null;
    }
    
    // Remover elemento (remocao logica)
    public void remover(int chave) {
        int i = 0;
        int posicao;
        
        while (i < m) {
            posicao = hash(chave, i);
            
            if (tabela[posicao] == null) {
                System.out.println("Chave " + chave + " nao encontrada");
                return;
            }
            
            if (tabela[posicao].chave == chave && !tabela[posicao].deletado) {
                tabela[posicao].deletado = true;
                n--;
                System.out.println("Chave " + chave + " removida da posicao " + posicao);
                return;
            }
            
            i++;
        }
        
        System.out.println("Chave " + chave + " nao encontrada");
    }
    
    // Imprimir tabela
    public void imprimir() {
        System.out.println("\n--- Tabela Hash ---");
        System.out.println("Tamanho: " + m);
        System.out.println("Elementos: " + n);
        System.out.println("Fator de carga: " + (double)n/m);
        System.out.println("-------------------");
        
        for (int i = 0; i < m; i++) {
            System.out.print("[" + i + "] ");
            if (tabela[i] == null) {
                System.out.println("vazio");
            } else if (tabela[i].deletado) {
                System.out.println("DELETADO");
            } else {
                System.out.println(tabela[i].chave + " -> " + tabela[i].valor);
            }
        }
        System.out.println();
    }
    
    // Programa principal
    public static void main(String[] args) {
        System.out.println("Tabela Hash - Dispersao Dupla\n");
        
        TabelaHash hash = new TabelaHash(11);
        
        System.out.println("=== Insercoes ===");
        hash.inserir(10, "dez");
        hash.inserir(22, "vinte e dois");
        hash.inserir(31, "trinta e um");
        hash.inserir(4, "quatro");
        hash.inserir(15, "quinze");
        hash.inserir(28, "vinte e oito");
        hash.inserir(17, "dezessete");
        
        hash.imprimir();
        
        System.out.println("=== Buscas ===");
        hash.buscar(22);
        hash.buscar(15);
        hash.buscar(99);
        
        System.out.println("\n=== Remocoes ===");
        hash.remover(22);
        hash.remover(4);
        
        hash.imprimir();
        
        System.out.println("=== Busca apos remocao ===");
        hash.buscar(22);
        hash.buscar(28);
        
        System.out.println("\n=== Nova insercao ===");
        hash.inserir(33, "trinta e tres");
        
        hash.imprimir();
    }
}