package com.bitcoinvisualizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Bitcoin_VisualizerServlet extends HttpServlet
{
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String s;
		String address = request.getParameter("block");
		BlockType block = new BlockType();
		try
		{
			s = downloadPage("http://blockexplorer.com/rawblock/" + address);
			block.parseBlock(s);

			StringBuilder output = new StringBuilder();

			output.append("Hash: " + block.getHash() + "\n");
			output.append("Version: " + block.getVersion() + "\n");
			output.append("Previous Block: " + block.getPrev_block() + "\n");
			output.append("Merkle Root: " + block.getMrkl_root() + "\n");
			output.append("Time: " + block.getTime().toGMTString() + "\n");
			output.append("Bits: " + block.getBits() + "\n");
			output.append("Hash attempts: " + block.getNonce() + "\n");
			output.append("Number of Transactions: " + block.getN_tx() + "\n");
			output.append("Size (in 1000b bits): " + block.getBits() + "\n");

			// output transactions
			for (int i = 0; i < block.getTransactions().size(); i++)
			{
				output.append("\tTransaction #" + i + "\n");
				output.append("\t\tHash: " + block.getTransactions().get(i).getHash() + "\n");
				output.append("\t\tVersion: " + block.getTransactions().get(i).getVersion() + "\n");
				output.append("\t\t# of incoming transactions: " + block.getTransactions().get(i).getVin_sz() + "\n");
				output.append("\t\t# of outgoing transactions: " + block.getTransactions().get(i).getVout_sz() + "\n");
				output.append("\t\tLock Time: " + block.getTransactions().get(i).getLock_time() + "\n");
				output.append("\t\tSize (in 1000b bits): " + block.getTransactions().get(i).getSize() + "\n");
				output.append("\t\tHash: " + block.getTransactions().get(i).getHash() + "\n");
				
				// output incoming address
				for (int j = 0; j < block.getTransactions().get(i).getIncoming_address().size(); j++)
				{
					output.append("\t\t\tIncoming Address #" + j + "\n");
					output.append("\t\t\t\tHash: " + block.getTransactions().get(i).getIncoming_address().get(j).getHash() + "\n");
					output.append("\t\t\t\tIndex: " + block.getTransactions().get(i).getIncoming_address().get(j).getN() + "\n");
					if (block.getTransactions().get(i).getIncoming_address().get(j).getCoinbase() != null)
						output.append("\t\t\t\tCoinbase: " + block.getTransactions().get(i).getIncoming_address().get(j).getCoinbase() + "\n");
					if (block.getTransactions().get(i).getIncoming_address().get(j).getScriptSig() != null)
						output.append("\t\t\t\tSript Signature: " + block.getTransactions().get(i).getIncoming_address().get(j).getScriptSig() + "\n");			
				}
				
				// output outgoing address
				for (int k = 0; k < block.getTransactions().get(i).getOutgoing_address().size(); k++)
				{
					output.append("\t\t\tOutgoing Address #" + k + "\n");
					output.append("\t\t\t\tValue: " + block.getTransactions().get(i).getOutgoing_address().get(k).getValue() + "\n");
					output.append("\t\t\t\tPublic Key Transaction Redemption: " + block.getTransactions().get(i).getOutgoing_address().get(k).getScriptPubKey() + "\n");
				}				
			}
			response.getWriter().write(output.toString());
		}

		catch (Exception e)
		{
			response.getWriter().write("Probably an invalid block\n");
			response.getWriter().write("Error: " + e);
		}
		
		
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		

	}

	private String downloadPage(String link) throws Exception
	{
		URL url = new URL(link);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder builder = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null)
		{
			builder.append(line + "\n");
		}

		reader.close();

		return builder.toString();
	}
}
