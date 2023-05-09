package vision.consultas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.MTAtendimenoEntrada;
import model.interfaces.InterEntrada;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;

public class VEntradaCON extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TableSimples table;
	private JLabel lbFiltro;
	private RoundJTextField edFiltro;
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private RoundButton btnFiltro;

	public VEntradaCON(List<MTAtendimenoEntrada> dados, InterEntrada inter) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 742, 385);
		setTitle("Consulta Antendimento - Entrada");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		edFiltro = new RoundJTextField();
		edFiltro.setColumns(10);

		lbFiltro = new JLabel("Nome: ");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new TableSimples(new Object[][] {}, new String[] { "Número ate.", "CPF", "Nome", "Data", "Nome Pet", "Espécie","Raça"});
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		atualizarTabela(dados, false);
		
		scrollPane.setViewportView(table);

		btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTAtendimenoEntrada dado = dados.get(modelIndex);
					inter.preencheDadosEntrada(dado);
					dispose();
				}
			}
		});

		btnExcluir = new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTAtendimenoEntrada dado = dados.get(modelIndex);
					inter.exluirAtendimento(dado.getBDIDENTRADA());
					dispose();
				}
			}
		});
		btnFiltro = new RoundButton("Filtro");
		btnFiltro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (edFiltro.getText().isEmpty()) {
					atualizarTabela(dados, false);
				} else {
					atualizarTabela(dados, true);
				}
				
				table.setRowSelectionInterval(0, 0);
			}
		});

		JPanel filterPanel = new JPanel();
		filterPanel.add(lbFiltro);
		filterPanel.add(edFiltro);
		filterPanel.add(btnFiltro);

		JPanel botoes = new JPanel();
		botoes.add(btnConfirmar);
		botoes.add(btnExcluir);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1));
		buttonsPanel.add(filterPanel);
		buttonsPanel.add(botoes);

		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
	}

	public void atualizarTabela(List<MTAtendimenoEntrada> dados, Boolean prFiltro) {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		table.limparTabela();

		for (MTAtendimenoEntrada dado : dados) {
			if (prFiltro && !dado.getBDNOMEUSER().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[][] rowData = {{ dado.getBDIDENTRADA(), dado.getBDCPF(), dado.getBDNOMEUSER(), dado.getBDDATAENT().format(FOMATTER), dado.getBDNOMEPET(), dado.getBDNOMEESPECIE(), dado.getBDNOMERACA()}};
			table.preencherTabela(rowData);
		}
	}
	
	public void desabilitaExcluir() {
		btnExcluir.setVisible(false);
	}
	
	public void desBotoes() {
		btnConfirmar.setVisible(false);
		btnExcluir.setVisible(false);
	}

}
