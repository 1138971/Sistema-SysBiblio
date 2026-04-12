import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private List<Livro> acervo = new ArrayList<>();

    public void cadastrar(Livro novoLivro) throws Exception {

        if (novoLivro == null)
            throw new Exception("Objeto Nulo");

        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty())
            throw new Exception("Título inválido!!!");

        novoLivro.setTitulo(novoLivro.getTitulo().trim().toUpperCase());

        if (novoLivro.getAnoPublicacao() < 1900
                || novoLivro.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano de publicação inválido");
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(novoLivro.getTitulo())
                    && livro.getAutor().equalsIgnoreCase(novoLivro.getAutor())
                    && livro.getAnoPublicacao() == novoLivro.getAnoPublicacao())
                throw new Exception("Já existe livro cadastrado com este Título, Autor e Ano de publicação");
        }
        acervo.add(novoLivro);

    }
    public List<Livro> listar() {
        return acervo;
    }


    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        titulo = titulo.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getTitulo().contains(titulo))
                livrosEncontrados.add(livro);
        }

        return livrosEncontrados;
    }

        public List<Livro> pesquisarPorAutor(String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        autor = autor.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getAutor().toUpperCase().contains(autor))
                livrosEncontrados.add(livro);
        }

        return livrosEncontrados;
    }

    public List<Livro> pesquisarPorAno(int ano) {
        List<Livro> livrosEncontrados = new ArrayList<>();

        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() == ano)
                livrosEncontrados.add(livro);
        }

        return livrosEncontrados;
    }

    
    public void remover(int indice) throws Exception {
    if (indice < 0 || indice >= acervo.size()){
            throw new Exception("Índice inválido!");
        }
    acervo.remove(indice);
    }

    public void editar(int indice, Livro livroAtualizado) throws Exception {

    if (indice < 0 || indice >= acervo.size()) {
        throw new Exception("Índice inválido!");
    }

    if (livroAtualizado == null)
        throw new Exception("Objeto Nulo");


    if (livroAtualizado.getTitulo() == null || livroAtualizado.getTitulo().isEmpty())
        throw new Exception("Título inválido!!!");

    livroAtualizado.setTitulo(livroAtualizado.getTitulo().trim().toUpperCase());


    if (livroAtualizado.getAnoPublicacao() < 1900
            || livroAtualizado.getAnoPublicacao() > LocalDate.now().getYear())
        throw new Exception("Ano de publicação inválido");


    for (int i = 0; i < acervo.size(); i++) {
        if (i == indice) continue;

        Livro livro = acervo.get(i);

        if (livro.getTitulo().equalsIgnoreCase(livroAtualizado.getTitulo())
                && livro.getAutor().equalsIgnoreCase(livroAtualizado.getAutor())
                && livro.getAnoPublicacao() == livroAtualizado.getAnoPublicacao()) {

            throw new Exception("Já existe livro com esses dados");
        }
    }
    public List<Livro> ordenarPorTitulo() {

    List<Livro> listaOrdenada = new ArrayList<>(acervo);

    listaOrdenada.sort((l1, l2) -> 
        l1.getTitulo().compareToIgnoreCase(l2.getTitulo())
    );

    return listaOrdenada;
    }

    public List<Livro> ordenarPorAno() {

    List<Livro> listaOrdenada = new ArrayList<>(acervo);

    listaOrdenada.sort((l1, l2) -> 
        Integer.compare(l1.getAnoPublicacao(), l2.getAnoPublicacao())
    );

    return listaOrdenada;

    }
        
    acervo.set(indice, livroAtualizado);
}
    
}

