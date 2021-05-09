/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import modeloConection.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelBeans.BeansMedico;

/**
 *
 * @author Technotronics
 */
public class DaoMedico {
    ConexaoBD conex = new ConexaoBD();
    BeansMedico mod = new BeansMedico();
    
    public void Salvar (BeansMedico mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into medicos(nome_medico,especialidade_medico,crm_medico) values (?,?,?)");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getEspecialidade());
            pst.setInt(3, mod.getCrm());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Dados/nErro:"+ex);
        }
        
        conex.desconecta();
    } 
    
 public BeansMedico buscaMedico(BeansMedico mod){
     conex.conexao();
     conex.executaSql("select * from medicos where nome_medico like'%"+mod.getPesquisa()+"%'");
        try {
            conex.rs.first();
            mod.setCod(conex.rs.getInt("cod_medico"));
            mod.setNome(conex.rs.getString("nome_medico"));
            mod.setEspecialidade(conex.rs.getString("especialidade_medico"));
            mod.setCrm(conex.rs.getInt("crm_medico"));   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Médico:"+ex);   
        }
     conex.desconecta();
     return mod;
 }   
}
