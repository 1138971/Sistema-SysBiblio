import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

LivroService service = new LivroService();


void main() {
    String menu = """
            ===== SysBiblio =====
            1 - Cadastrar Livro
            2 - Listar Livros
            3 - Pesquisar Livro
            4 - Remover Livro
            5 - Editar Livro
            6 - Ordenar Livros
            0 - Sair
            """;

    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite uma opção: ");
        try {
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> pesquisar();
                case 4 -> remover();
                case 5 -> editar();
                case 6 -> ordenar();
                case 0 -> IO.println("Até breve!!!");
                default -> IO.println("Opção Inválida");
            }
        } catch (Exception e) {
            IO.println("ERRO: " + e.getMessage());
        }
        IO.readln("Pressione Enter para continuar...");
    } while (opcao != 0);
}

void cadastrar() throws Exception {
    String titulo = Input.scanString("Digite o título do livro: ");
    String autor = Input.scanString("Digite o autor do livro: ");
    int anoPublicacao = Input.scanInt("Digite o ano de publicação do livro: ");
    int numeroPaginas = Input.scanInt("Digite o número de páginas do livro: ");

    Livro novoLivro = new Livro(titulo, autor, anoPublicacao, numeroPaginas);

    service.cadastrar(novoLivro);

    IO.println("Livro cadastrado com sucesso!!!");
}

void listar() {

    List<Livro> livros = service.listar();

    imprimirLista(livros);

}

void pesquisar() {

    String pesquisa = Input.scanString("Digite parte do título: ");
    
    List<Livro> livros = service.pesquisar(pesquisa);

    imprimirLista(livros);
}

void remover() throws Exception {

    List<Livro> livros = service.listar();
    if (livros.isEmpty()) {
        IO.println("Nenhum livro cadastrado!");
        return;
    }
    imprimirLista(livros);

    int opcao = Input.scanInt("Digite o número do livro que deseja remover: ");

    service.remover(opcao - 1);

    IO.println("Livro removido com sucesso!");
}

void editar() throws Exception {

    List<Livro> livros = service.listar();

    if (livros.isEmpty()) {
        IO.println("Nenhum livro cadastrado!");
        return;
    }

    imprimirLista(livros);

    int opcao = Input.scanInt("Digite o número do livro que deseja editar: ");

    int indice = opcao - 1;

    String titulo = Input.scanString("Novo título: ");
    String autor = Input.scanString("Novo autor: ");
    int anoPublicacao = Input.scanInt("Novo ano de publicação: ");
    int numeroPaginas = Input.scanInt("Novo número de páginas: ");

    Livro livroAtualizado = new Livro(titulo, autor, anoPublicacao, numeroPaginas);

    service.editar(indice, livroAtualizado);

    IO.println("Livro atualizado com sucesso!");
}

void imprimirLista(List<Livro> livros) {
    if (livros.isEmpty()) {
        IO.println("Nenhum livro encontrado!");
        return;
    }

    int i = 1;
    for (Livro livro : livros) {
        IO.println(i++  + " - " + livro);
    }
}

void ordenar() {

    String menu = """
            --- Ordenação de Livros ---
            1 - Ordenar por título
            2 - Ordenar por ano de publicação
            """;

    IO.println(menu);

    int opcao = Input.scanInt("Escolha uma opção: ");

    List<Livro> livros = new ArrayList<>();

    switch (opcao) {
        case 1 -> livros = service.ordenarPorTitulo();
        case 2 -> livros = service.ordenarPorAno();
        default -> {
            IO.println("Opção inválida!");
            return;
        }
    }

    imprimirLista(livros);
}
