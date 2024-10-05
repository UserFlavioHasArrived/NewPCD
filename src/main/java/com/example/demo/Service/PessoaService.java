package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.Sexo;
import com.example.demo.Form.Pessoa.PessoaForm;
import com.example.demo.Model.Bairro;
import com.example.demo.Model.Cidade;
import com.example.demo.Model.Endereco;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.BairroRepository;
import com.example.demo.Repository.CidadeRepository;
import com.example.demo.Repository.EnderecoRepository;
import com.example.demo.Repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa create(PessoaForm pessoaForm){
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(pessoaForm.getNome());
        pessoa.setNascimento(pessoaForm.getNascimento());
        pessoa.setDeficiencia(pessoaForm.getDeficiencia());

        Sexo sexo = Sexo.fromCodigo(pessoaForm.getSexo());
        pessoa.setSexo(sexo);

        Endereco endereco = new Endereco();

        endereco.setCep(pessoaForm.getCep());
        endereco.setLogradouro(pessoaForm.getLogradouro());

        Cidade cidade = this.cidadeRepository.findCidadeByNome(pessoaForm.getCidade());
        Bairro bairro = this.bairroRepository.findBairroByNomeAndCidade(pessoaForm.getBairro(), cidade.getId());

        endereco.setBairro(bairro);
        endereco.setComplemento(pessoaForm.getComplemento());
        endereco.setNumero(pessoaForm.getNumero());

        this.enderecoRepository.save(endereco);

        pessoa.setEndereco(endereco);

        this.pessoaRepository.save(pessoa);
        return pessoa;

    }

    public Pessoa update(PessoaForm pessoaForm, Long id){
        //Entidade Pessoa
        Pessoa pessoa = this.pessoaRepository.findById(id).orElseThrow();

        pessoa.setNome(pessoaForm.getNome());
        pessoa.setNascimento(pessoaForm.getNascimento());
        pessoa.setDeficiencia(pessoaForm.getDeficiencia());

        Sexo sexo = Sexo.fromCodigo(pessoaForm.getSexo());
        pessoa.setSexo(sexo);

        //Entidade Endereço
        Endereco endereco = pessoa.getEndereco();

        endereco.setCep(pessoaForm.getCep());
        endereco.setLogradouro(pessoaForm.getLogradouro());

        Cidade cidade = this.cidadeRepository.findCidadeByNome(pessoaForm.getCidade());
        Bairro bairro = this.bairroRepository.findBairroByNomeAndCidade(pessoaForm.getBairro(), cidade.getId());

        endereco.setBairro(bairro);
        endereco.setComplemento(pessoaForm.getComplemento());
        endereco.setNumero(pessoaForm.getNumero());

        this.enderecoRepository.save(endereco);

        pessoa.setEndereco(endereco);

        this.pessoaRepository.save(pessoa);
        return pessoa;

    }

}
