import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    final static String ARQUIVO = "./src/contacts.txt";

    // função que vai ler os contatos que estão no arquivo txt e armazenar no array.
    // caso não exista o arquivo ele cria
    // case não seja possivel ler o arquivo ele escreve uma mensagem de erro e retorna um array vazio
    public static ArrayList<Contato> lerContatos(){
        ArrayList<Contato> contatos =  new ArrayList<>();
        File arquivo = new File(ARQUIVO);
        try {
            if (arquivo.exists()){
                BufferedReader buffRead = new BufferedReader(new FileReader(arquivo));
                String linha = null;
                while (true){
                    linha = buffRead.readLine();
                    if (linha != null){
                        Long idContato = Long.parseLong(linha);
                        String nome = buffRead.readLine();
                        String sobreNome = buffRead.readLine();
                        linha = buffRead.readLine();
                        Long lengthTelefones = Long.parseLong(linha);
                        Contato novoContato = new Contato(idContato, nome,sobreNome);
                        for(int i = 1; i <= lengthTelefones;i++){
                            String ddd = buffRead.readLine();
                            long numero = Integer.parseInt(buffRead.readLine());
                            novoContato.inserirTelefone(ddd, numero);
                        }
                        contatos.add(novoContato);
                    }else{
                        break;
                    }
                }
                buffRead.close();
                return contatos;
            }else{
                if (arquivo.createNewFile()) {
                    System.out.println("CRIANDO NOVA AGENDA");
                }else{
                    System.out.println("AGENDA VAZIA");
                }
                return contatos;
            }
        }catch (Exception e){
            System.out.println("NÃO FOI pOSSIVEL LER CONTATO: "+e.getMessage());
            return new ArrayList<Contato>();
        }
    }

    // função que vai gravar o array de contatos no arquivo txt.
    // caso não seja possivel ele imprime uma uma mensagem de erro e retorna false
    public static void gravarContatos(ArrayList<Contato> contatos){
        try {
            BufferedWriter buffewriter = new BufferedWriter(new FileWriter(ARQUIVO));
            for (Contato contato : contatos){
                buffewriter.append(contato.getId()+"\n");
                buffewriter.append((contato.getNome()+"\n"));
                buffewriter.append(contato.getSobreNome()+"\n");
                buffewriter.append(contato.getLengthTelefones()+"\n");
                for (Telefone telefone : contato.getTelefones()){
                    buffewriter.append(telefone.getDdd()+"\n");
                    buffewriter.append(telefone.getNumero()+"\n");
                }
            }
            buffewriter.close();
        }catch (Exception e){
            System.out.println("NÃO FOI pOSSIVEL GRAVAR CONTATOS: D"+e.getMessage());
        }
    }

    // responsabem por adcinar um novo contato no array
    // caso não seja possível ele retorna erro.
    public static boolean adcionarContato(Scanner scan, ArrayList<Contato> contatos,long proximoId){
        try {
            boolean result = true;
            listarContatos(contatos, 2);
            System.out.print("vamos começar pelo nome: ");
            String nome = scan.nextLine();
            System.out.print("Sobre nome: ");
            String sobreNome = scan.nextLine();
            Contato novo = new Contato(proximoId,nome,sobreNome);
            for(Contato contato : contatos) {
                if (contato.equals(novo)) {
                    System.out.print("Conatato com mesmo nome");
                    result = false;
                    break;
                }
            }
            boolean equal = false;
            while(true){
                System.out.println("vamos adcionar um numero para o novo contato; ");
                System.out.print("começe com o ddd: ");
                String ddd = scan.nextLine();
                System.out.print("agora digite o numero: ");
                Long numero = scan.nextLong();
                for (Contato contato : contatos){
                    if (contato.existsTelefone(ddd,numero)){
                        System.out.println("numero ja existente em outro contato; ");
                        equal = true;
                        break;
                    }
                }
                if (equal) {
                    continue;
                }else if(novo.inserirTelefone(ddd,numero)){
                    result = true;
                    contatos.add(novo);
                    break;
                }else{
                    System.out.println("ocorreu um erro;");
                }
            }
            return result;
        }catch(Exception e){
            return false;
        }
    }

    // remove um contato de dentro do array tendo como parametro o id do contato
    public static boolean removerContato(Scanner scan, ArrayList<Contato> contatos){
        try {
            while (true){
                listarContatos(contatos, 2);
                System.out.print("com o id do contato selecione qual vc deseja remover: ");
                int operacao;
                try{
                    operacao = scan.nextInt();
                    String next = scan.nextLine();
                    if(operacao == 0){
                        break;
                    }
                    for (Contato contatoARemover : contatos) {
                        if (contatoARemover.getId() == operacao) {
                            contatos.remove(contatoARemover);
                            return true;
                        }
                    }
                    break;
                }catch (Exception e){
                    System.out.println("digite um id valido:");
                    String next = scan.nextLine();
                }
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }

    // responsável por:
    // adcionar num numero novo à algum contato existente.
    // modificar nome ou sobre nome do contato
    // moficica um telefone tendo como referencia o id
    // remover algum numero que o contato tenha
    public static boolean alterarContato(Scanner scan,ArrayList<Contato> contatos){
        try {
            listarContatos(contatos, 2);
            while (true){
                System.out.print("selecione o id do contato que vc quer modificar: ");
                int idMoficar;
                try{
                    idMoficar = scan.nextInt();
                    String next = scan.nextLine();
                }catch (Exception e){
                    idMoficar = 0;
                    String next = scan.nextLine();
                }
                System.out.print("1 - adcionar um numero\n" +
                        "2 - modificar nome \n" +
                        "3 - modificar telefone\n" +
                        "4 - remover telefone\n"+
                        "0 - volta ao menu anterior"+
                        "digite a operaçãoa: ");
                int operacao;
                try{
                    operacao = scan.nextInt();
                    String nex = scan.nextLine();
                }catch (Exception e){
                    operacao = 0;
                    String next = scan.nextLine();
                }
                if (operacao == 0){
                    break;
                }
                for (Contato contatoModificar : contatos){
                    if (contatoModificar.getId() == idMoficar){
                        switch (operacao){
                            case 1:

                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            default :
                                System.out.println("operação invalido começar de novo!!\n");
                        }
                    }
                }
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    // função para imprimir o cabeçalho do menu
    public  static  void printCabecalho(ArrayList<Contato> contatos){
        System.out.println(">>>> Menu <<<<\n1 - listar contatos\n" +
                "2 - Adicionar Contato\n"+
                "3 - Remover Contato\n" +
                "4 - Editar Contato\n" +
                "5 - Sair");
    }

    // função que lista os contatos tendo como condição 1 ou 2
    // 1 para imprimir só os nomes e id dos contatos,
    // 2 para imprimir com os numeros
    public static void listarContatos(ArrayList<Contato> contatos, int condicao){
        System.out.println(">>>> Contatos <<<<");
        System.out.println("Id | Nome");
        for(Contato contato : contatos){
            System.out.println(contato.getId()+" | "+contato.getNome()+" " +contato.getSobreNome());
            if (condicao == 2){
                contato.listarNumero();
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Contato> contatos;
        contatos = lerContatos();
        long proximoId = contatos.size()+1;
        Scanner scan = new Scanner(System.in);
        boolean rodando = true;
        System.out.println("##################\n##### AGENDA #####\n##################");
        listarContatos(contatos, 1);
        while (rodando) {
            printCabecalho(contatos);
            int operacao;
            try{
                System.out.print("digite a operação: ");
                operacao = scan.nextInt();
                String next = scan.nextLine();
            }catch (Exception e){
                operacao = 0;
                String next = scan.nextLine();
            }
            switch (operacao){
                case 1:
                    listarContatos(contatos, 2);
                    break;
                case 2:
                    if(adcionarContato(scan, contatos, proximoId)){
                        proximoId++;
                        System.out.println("adcionado com sucesso");
                    }else{
                        System.out.println("contato não adcionado");
                    }
                    break;
                case 3:
                    if (removerContato(scan, contatos)){
                        System.out.println("removido com sucesso");
                    }else{
                        System.out.println("contato não removido");
                    }
                    break;
                case 4:
                    if (alterarContato(scan, contatos)){
                        System.out.println("alterado com sucesso");
                    }else{
                        System.out.println("contato não removido");
                    }
                    break;
                case 5:
                    rodando = false;
                    break;
                default:
                    System.out.println("comando invalido, digite a operação valida no menu");
            }
        }
        gravarContatos(contatos);
        scan.close();
    }
}