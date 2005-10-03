/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import java.util.Calendar;
import java.util.Date;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.Provider;
import com.inetvod.common.dbdata.ProviderID;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowCost;
import com.inetvod.common.dbdata.ShowID;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.player.rqdata.License;
import com.inetvod.player.rqdata.LicenseMethod;

public class RentShowRqst extends SessionRequestable
{
	/* Fields */
	protected ShowID fShowID;
	protected ProviderID fProviderID;
	protected ShowCost fApprovedCost;

	/* Constuction Methods */
	public RentShowRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		RentShowResp response;
		Show show;
		Provider provider;
		ShowProvider showProvider;
		RentedShow rentedShow;

		response = new RentShowResp();

		show = Show.get(fShowID);
		provider = Provider.get(fProviderID);
		rentedShow = RentedShow.newInstance(fMember, show, provider);
		showProvider = ShowProvider.getByShowIDProviderID(fShowID, fProviderID);
		//TODO: fetch this from Provider API
		rentedShow.setShowURL("http://api.inetvod.com/mce/videos/TestVideo.wmv");
		rentedShow.setShowCost(showProvider.getShowCost());
//		rentedShow.setShowCost(new ShowCost());
//		rentedShow.getShowCost().setShowCostType(ShowCostType.PayPerView);
//		rentedShow.getShowCost().setCost(new Money(CurrencyID.USD, new Double(4.95)));
//		rentedShow.getShowCost().setCostDisplay("$4.95");
//		rentedShow.setRentalHours(new Short((short)48));
		rentedShow.setRentedOn(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR, showProvider.getShowCost().getRentalHours().intValue());
		rentedShow.setAvailableUntil(cal.getTime());
		//TODO: fetch this from Provider API
		rentedShow.update();

		response.setRentedShowID(rentedShow.getRentedShowID());
		License license = new License();
		license.setLicenseMethod(LicenseMethod.URLOnly);
		license.setShowURL(rentedShow.getShowURL());
		response.setLicense(license);

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowID = (ShowID)reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = (ProviderID)reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fApprovedCost = reader.readObject("ApprovedCost", ShowCost.CtorDataReader);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeObject("ApprovedCost", fApprovedCost);
	}
}
