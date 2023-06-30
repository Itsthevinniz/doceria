package br.com.tech4me.doceria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.doceria.model.Produtos;

public interface ProdutosRepository extends MongoRepository<Produtos, String> {
    
}
