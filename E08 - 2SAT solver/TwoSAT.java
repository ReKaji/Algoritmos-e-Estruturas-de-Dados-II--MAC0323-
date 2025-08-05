/******************************************************************************
 *
 * MAC0323 ALGORITMOS E ESTRUTURAS DE DADOS II
 * Aluno: Renan Ryu Kajihara
 * Numero USP: 14605762
 * Tarefa: Exercício E09
 * Data: 08/06/2024
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;




public class TwoSAT {

    private boolean[] marked;     
    private int[] id;             
    private int count;
    private Stack<Integer> pilha;
    private boolean satisfazivel;
    private Digraph D;
    private boolean[] assignment;
    private Stack<Integer> cycle;
    private int ind_prob;
    private String unSATProof;
    private boolean parada;
    
    public TwoSAT (ImplicationGraph G){
        D = G.g();
        count=1;
        satisfazivel=true;
        marked = new boolean[D.V()];
        id = new int[D.V()];
        boolean[] marcado_passo_1 = new boolean[D.V()];
        pilha = new Stack<Integer>();
        cycle = new Stack<Integer>();
       
        for (int i=-0; i<D.V();i++){
            
        
            if (marcado_passo_1[i]==false) preenche_pilha(D,i,marcado_passo_1,pilha);
        }

        Digraph D_reverso = (D.reverse());

        for (int i=0;i<D_reverso.V();i++){
            int v = pilha.pop();
            if (marked[v]==false){
                dfs(D_reverso,v);
                count++;
            }
        }
        for (int i = 0; i < D.V(); i=i+2) {
            if (id[i] == id[i+1]) {
                ind_prob=i;
                satisfazivel = false;
                break;
            }
        }

        assignment = new boolean[G.N()+1];
        if (hasSolution()){
            ass(true);
            boolean corretude = true;
            for (int i = 0; i<D.V();i++){
                for (int w : D.adj(i)){
                    if (i%2==w%2){
                        if (assignment[i/2]!=assignment[w/2]) {
                            corretude= false;
                        
                            break;
                        }
                    }
                    else{
                        if (assignment[i/2]==assignment[w/2]) {
                            corretude= false;
                            
                            break;
                        }
                    }
                }
            }
            if (corretude ==false) ass(false);}
        
        else{
            boolean[] marked_1 = new boolean[D.V()];
            int[] edgeTo = new int[D.V()];
            marked_1[ind_prob]=true;
            
            for (int w:D.adj(ind_prob)){
                if (!marked_1[w]){
                    edgeTo[w]=ind_prob;
                    certificado(D,w,ind_prob,edgeTo,marked_1);}}
            
            StringBuilder sb = new StringBuilder();
            int indice;
            while(!cycle.isEmpty()){
                indice = cycle.pop();
                if (indice%2==0) indice = indice/2+1;
                else indice = -(indice/2+1);
                sb.append(indice);
                if (!cycle.isEmpty()) sb.append(" => ");
            }
            unSATProof =  sb.toString();

           
            
            parada=false;
            boolean[] marked_2 = new boolean[D.V()];
            int[] edgeTo2 = new int[D.V()];
            ind_prob=ind_prob+1;
            marked_2[ind_prob]=true;
            for (int w:D.adj(ind_prob)){
                if (!marked_2[w]){
                    edgeTo2[w]=ind_prob;
                    certificado(D,w,ind_prob,edgeTo2,marked_2);}}
            
            sb = new StringBuilder();
            while (!cycle.isEmpty()) {
                indice = cycle.pop();
                if (indice%2==0) indice = indice/2+1;
                else indice = -(indice/2+1);
                sb.append(indice);
                if (!cycle.isEmpty()) sb.append(" => ");
                }
            unSATProof += "\n" + sb.toString();
                
                
            }
                    

    }

    private void preenche_pilha(Digraph G, int v,  boolean[] marked, Stack<Integer> pilha){
        marked[v]=true;
        for (int w : G.adj(v)) {
            
            if (!marked[w]) {
                
                preenche_pilha (G, w, marked, pilha);
            }
        }
        pilha.push(v);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    private void ass(boolean determina){
        boolean[] mark_ass = new boolean[(D.V()/2)+2];
        int g = D.V();
        int i = 1;
        while (g>0){
            for (int j=0;j<D.V();j++){
                if (id[j]==i && mark_ass[j/2+1]==false){
                    if (j%2==0) {
                        assignment[j/2+1]=determina;
                        g=g-2;
                        mark_ass[j/2+1]=true;
                    }
                    else if (mark_ass[j/2+1]==false){
                        assignment[j/2+1]= !determina;
                        g=g-2;
                        mark_ass[j/2+1]=true;
                    }
                }
            }
            i++;}
        }

        private void certificado (Digraph G, int v, int s, int[] edgeTo, boolean[] marked){
            marked[v]=true;
            if (!parada){
                for (int w:G.adj(v)){
                    
                    
                    if ((w==s+1 && s%2==0) || (w==s-1 && s%2==1)){
                        edgeTo[w]=v;
                        
                        
                        for (int x = w; x != s; x = edgeTo[x]) {
                            
                            cycle.push(x);
                        }
                        cycle.push(s);
                        parada = true;
                        return;          
                    }
        
                    else if(!marked[w]){
                        edgeTo[w]=v;
                        certificado(G,w,s,edgeTo, marked);
                        return;
        
                    }
                
                }
            return;}
            
        }
    private boolean StrongConnected(int v, int w){
        if (id[v]!=0) return id[v]==id[w];
        else return false;
    }
    public boolean hasSolution(){
        return satisfazivel;
    }

    public  boolean[] assignment(){
        return assignment;
    }
    public String unSATProof(){
        return unSATProof;
    }
}
