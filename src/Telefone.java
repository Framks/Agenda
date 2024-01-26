public class Telefone {
    private Long id;
    private String ddd;
    private Long numero;

    public Telefone(Long id, String ddd, long numero){
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
    }

    public Long getId(){
        return this.id;
    }
    public String getDdd(){
        return this.ddd;
    }
    public Long getNumero(){
        return this.numero;
    }

    public void setDdd(String ddd){
        this.ddd = ddd;
    }

    public void setNumero(Long numero){
        this.numero = numero;
    }

    // verificar se um telefone é igual a esse
    public boolean equals(Telefone telefone){
        if(!telefone.getDdd().equals(this.getDdd())){
            return false;
        }else if(telefone.getNumero() != this.getNumero()){
            return false;
        }
        return true;
    }
}
