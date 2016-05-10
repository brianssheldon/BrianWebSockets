package org.bubba.websockets;

import java.io.Serializable;

public class Yorkie implements Serializable
{
	private String face = "";
	private String finger = "";
	private String taco = "";
	private int howMany = 0;
	private boolean isThatTooMany = false;

	public Yorkie(String facex, String fingerx, String tacox, int howManyx, boolean isThatTooManyx)
	{
		face = facex;
		finger = fingerx;
		taco = tacox;
		howMany = howManyx;
		isThatTooMany = isThatTooManyx;
	}

	public String getFace()
	{
		return face;
	}

	public void setFace(String face)
	{
		this.face = face;
	}

	public String getFinger()
	{
		return finger;
	}

	public void setFinger(String finger)
	{
		this.finger = finger;
	}

	public String getTaco()
	{
		return taco;
	}

	public void setTaco(String taco)
	{
		this.taco = taco;
	}

	public int getHowMany()
	{
		return howMany;
	}

	public void setHowMany(int howMany)
	{
		this.howMany = howMany;
	}

	public boolean isIsThatTooMany()
	{
		return isThatTooMany;
	}

	public void setIsThatTooMany(boolean isThatTooMany)
	{
		this.isThatTooMany = isThatTooMany;
	}
	
	
}

/* ***********************************************************
 *                                                           *
 * Copyright (C) 2015 The Hertz Corporation                  *
 * All Rights Reserved. (Unpublished.)                       *
 * The information contained herein is confidential and      *
 * proprietary to The Hertz Corporation and may not be       *
 * duplicated, disclosed to third parties, or used for any   *
 * purpose not expressly authorized by it.  Any unauthorized *
 * use, duplication or disclosure is prohibited by law.      *
 *                                                           *
 ************************************************************/