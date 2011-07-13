package com.bitcoinvisualizer;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class OutgoingAddressType
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private double value;
	
	@Persistent
	private String scriptPubKey;

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
	 * @return	The BTC sent by this output.
	 */
	public double getValue()
	{
		return value;
	}

	/**
	 * 
	 * @param value The BTC sent by this output.
	 */
	public void setValue(double value)
	{
		this.value = value;
	}

	/**
	 * 
	 * @return	This script specifies the conditions that must be met by someone attempting to redeem this output. Usually it contains a hash160 (Bitcoin address) or a public key.
	 */
	public String getScriptPubKey()
	{
		return scriptPubKey;
	}

	/**
	 * 
	 * @param scriptPubKey This script specifies the conditions that must be met by someone attempting to redeem this output. Usually it contains a hash160 (Bitcoin address) or a public key.
	 */
	public void setScriptPubKey(String scriptPubKey)
	{
		this.scriptPubKey = scriptPubKey;
	}
	
	
}
