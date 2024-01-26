import java.util.*;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private Long lengthTelefones;
    private ArrayList<Telefone> telefones;

    public Contato(Long id, String nome, String sobreNome) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = new ArrayList<Telefone>();
        this.lengthTelefones = 0L;
    }

    // verifica se existe um telefone nos seus numeros. usando o ddd e o numero
    public boolean existsTelefone(String ddd, Long numero) {
        Telefone novo = new Telefone(0L, ddd, numero);
        boolean exists = false;
        for (Telefone telefone : this.telefones) {
            if (telefone.equals(novo)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    // insere um telefone novo
    public boolean inserirTelefone(String ddd, Long numero) {
        Telefone novo = new Telefone((lengthTelefones + 1), ddd, numero);
        lengthTelefones++;
        this.telefones.add(novo);
        return true;
    }

    // remove um telefone da lista de numeros, usando o id como referência
    public boolean removerTelefone(long id) {
        for (Telefone telefoneRemover : this.telefones) {
            if (telefoneRemover.getId() == id) {
                this.telefones.remove(telefoneRemover);
                this.lengthTelefones--;
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return this.id;
    }

    // retorna o nome do contato
    public String getNome() {
        return this.nome;
    }

    // retorna o sobre Nome do contato
    public String getSobreNome() {
        return this.sobreNome;
    }

    // altera o nome do contato
    public void setNome(String nome) {
        this.nome = nome;
    }

    // altera o sobre nome do contato
    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    // retorna a quantidade de telefones que o contato tem
    public Long getLengthTelefones() {
        return this.lengthTelefones;
    }

    // retorna o array com todos os telefones do contato
    public ArrayList<Telefone> getTelefones() {
        return this.telefones;
    }

    // verifica se um contato é igual ao outro.
    public boolean equals(Contato novo) {
        String nomeCompleto = this.getNome() + this.getSobreNome();
        String novoNomeCompleto = novo.getNome() + this.getSobreNome();
        if (nomeCompleto.equals(novoNomeCompleto)) {
            return true;
        } else {
            return false;
        }
    }

    // listar os numeros que o contato tem.
    public void listarNumero() {
        System.out.print("Telefones: ");
        for (Telefone telefone : this.getTelefones()) {
            System.out.print("id:  " + telefone.getId() + " (" + telefone.getDdd() + ") " + telefone.getNumero() + "\t");
        }
        System.out.println(" ");
    }
}
