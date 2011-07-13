package com.bitcoinvisualizer;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class IncomingAddressType
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String hash;

	@Persistent
	private long n;

	@Persistent
	private String scriptSig;
	
	@Persistent
	private String coinbase;

	public Key getKey()
	{
		return key;
	}

	public void setKey(Key key)
	{
		this.key = key;
	}

	/**
	 * 
	 * @return Full hash of this transaction
	 */
	public String getHash()
	{
		return hash;
	}

	/**
	 * 
	 * @param hash
	 *            Full hash of this transaction
	 */
	public void setHash(String hash)
	{
		this.hash = hash;
	}

	/**
	 * 
	 * @return The index of where this particular address resides in the
	 *         transaction list.
	 */
	public long getN()
	{
		return n;
	}

	/**
	 * 
	 * @param n
	 *            The index of where this particular address resides in the
	 *            transaction list.
	 */
	public void setN(long n)
	{
		this.n = n;
	}

	/**
	 * 
	 * @return This script is matched with the referenced output's scriptPubKey.
	 *         It usually contains a signature, and possibly a public key.
	 *         ScriptSigs of generation inputs are sometimes called the
	 *         'coinbase' parameter, and they contain the current compact target
	 *         and the extraNonce variable
	 */
	public String getScriptSig()
	{
		return scriptSig;
	}

	/**
	 * 
	 * @param scriptSig
	 *            This script is matched with the referenced output's
	 *            scriptPubKey. It usually contains a signature, and possibly a
	 *            public key. ScriptSigs of generation inputs are sometimes
	 *            called the 'coinbase' parameter, and they contain the current
	 *            compact target and the extraNonce variable
	 */
	public void setScriptSig(String scriptSig)
	{
		this.scriptSig = scriptSig;
	}
	
	public String getCoinbase()
	{
		return coinbase;
	}

	public void setCoinbase(String coinbase)
	{
		this.coinbase = coinbase;
	}

}
