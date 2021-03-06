/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Controller;

import br.com.F1.Domain.Equipe;
import br.com.F1.Domain.Funcionario;
import br.com.F1.Service.EquipeService;
import br.com.F1.Service.FuncionarioService;
import br.com.F1.Util.Mensagens;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="funcionarioMB")
@SessionScoped
public class FuncionarioController implements Serializable{
    private List<Funcionario> listaFuncionario;
    private Funcionario funcionario;
    private FuncionarioService funcionarioService = new FuncionarioService();
    private EquipeService equipeService = new EquipeService();
    private List<Equipe> equipes;

    public FuncionarioController() {
        listar();
        this.funcionario = new Funcionario();
    }
    
    public void listar(){
        listaFuncionario = funcionarioService.Listar();//vai ser prenchida pelo service ele pede o dao e o dao buca no banco e manda para o controller.
    }

    public String novo(){
        this.equipes = equipeService.Listar();
        this.funcionario = new Funcionario();
        return "new.xhtml?faces-redirect=true";
    }
    
    public String salva(){
        if(funcionarioService.inserir(funcionario)){
            Mensagens.mensagemSucesso("Sucesso", "Registro salvo com sucesso");
            listar();
            return "list.xhtml?faces-redirect=true";
        }
        Mensagens.mensagemErro("Erro", "Ocorreu um erro ao salvar o registro.");
        return null ;
        
    }
    
    public String editar(Funcionario funcionario){
        this.funcionario = funcionario;
        this.equipes = equipeService.Listar();

        return "alter.xhtml?faces-redirect=true";
    }
    
    public String excluir( Funcionario funcionario){
        if(funcionarioService.excluir(funcionario)){
            Mensagens.mensagemSucesso("Sucesso", "Registro salvo com sucesso");
            this.funcionario = new Funcionario();
            listar();
            return voltar();
        }
        Mensagens.mensagemErro("Erro", "Ocorreu um erro ao excluir o registro.");
        return null ;
    }
    
    public String alterar(){
        funcionarioService.alterar(funcionario);
        listar();
        return voltar();
    }
    
    public String voltar(){
        return "list.xhtml?faces-redirect=true";
    }
    
    public String cancelar(){
        return "list.xhtml?faces-redirect=true";
    }

    public List<Funcionario> getListaFuncionario() {
        return listaFuncionario;
    }

    public void setListaFuncionario(List<Funcionario> listaFuncionario) {
        this.listaFuncionario = listaFuncionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FuncionarioService getFuncionarioService() {
        return funcionarioService;
    }

    public void setFuncionarioService(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }
    
    
}
