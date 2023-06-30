package br.com.tech4me.doceria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.doceria.model.Produtos;
import br.com.tech4me.doceria.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private ProdutosRepository repositorio;


    List<Produtos> produtos = new ArrayList<>();

@GetMapping
public List<Produtos> obterProdutos(){
    return repositorio.findAll();
}

@GetMapping("/{id}")
    public Produtos consultarPorId(@PathVariable String id) {
        Produtos produto = repositorio.findById(id).orElse(null);
        
        return produto;
    }

@PostMapping
public ResponseEntity<String> cadastrarProduto(@RequestBody Produtos produto){
    repositorio.save(produto);
    String mensagem = String.format("Categoria: %s - Produto %s cadastrado com sucesso!",
    produto.getTipo(), produto.getDescricao());

    return new ResponseEntity<>(mensagem, HttpStatus.CREATED);

}

@GetMapping("/{descricao}")
public Produtos obterProdutoPorNome(@PathVariable String descricao) {
    Produtos produto = produtos.stream()
    .filter(p -> p.getDescricao().equalsIgnoreCase(descricao))
    .findFirst().orElse(null);
return produto;
}

@DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable String id) {
        repositorio.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


@PutMapping("/{id}")
    public Produtos atualizarProduto(@PathVariable String id, @RequestBody Produtos novoProduto) {
        // Localizar
        Produtos antigoProduto = repositorio.findById(id).orElse(null);
    if (antigoProduto != null) {
            antigoProduto.setTipo(novoProduto.getTipo());
            antigoProduto.setDescricao(novoProduto.getDescricao());
            antigoProduto.setValor(novoProduto.getValor());
            repositorio.save(antigoProduto);
        }
        // devolver o produto
        return antigoProduto;
    }
}