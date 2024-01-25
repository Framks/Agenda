import java.util.*;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private Long lengthTelefones;
    private ArrayList<Telefone> telefones;

    public Contato(Long id,String nome, String sobreNome){
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = new ArrayList<Telefone>();
        this.lengthTelefones = 0L;
    }

    public boolean existsTelefone(String ddd, Long numero){
        Telefone novo = new Telefone(0L, ddd, numero);
        boolean exists = false;
        for (Telefone telefone : this.telefones){
            if (telefone.equals(novo)){
                exists = true;
                break;
            }
        }
        return exists;
    }

    public boolean inserirTelefone(String ddd, Long numero){
        Telefone novo = new Telefone((lengthTelefones + 1), ddd, numero);
        lengthTelefones++;
        this.telefones.add(novo);
        return true;
    }

    public boolean removerTelefone(long id){
        for (Telefone telefoneRemover : this.telefones){
            if (telefoneRemover.getId() == id){
                this.telefones.remove(telefoneRemover);
                this.lengthTelefones--;
                return true;
            }
        }
        return false;
    }

    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getSobreNome(){
        return this.sobreNome;
    }

    public Long getLengthTelefones(){
        return this.lengthTelefones;
    }

    public ArrayList<Telefone> getTelefones(){
        return this.telefones;
    }

    public boolean equals(Contato novo){
        String nomeCompleto = this.getNome() + this.getSobreNome();
        String novoNomeCompleto = novo.getNome() + this.getSobreNome();
        if(nomeCompleto.equals(novoNomeCompleto)){
            return true;
        }else{
            return false;
        }
    }

    public void listarNumero(){
        System.out.print("Telefones: ");
        for(Telefone telefone : this.getTelefones()){
            System.out.print("("+ telefone.getDdd() +") "+ telefone.getNumero()+"\t");
        }
        System.out.println(" ");
    }
}
