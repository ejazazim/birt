
/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.birt.data.engine.impl.group;

import java.util.Date;

import org.eclipse.birt.core.data.DataTypeUtil;
import org.eclipse.birt.core.exception.BirtException;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;

/**
 * This calculator is used to calculate a datetime group key basing group interval.
 */

abstract class DateGroupCalculator extends GroupCalculator
{
	
	protected Date defaultStart;
	protected ULocale locale;
	protected DateTimeUtil dateTimeUtil;
	private int range;
	
	/**
	 * 
	 * @param intervalStart
	 * @param intervalRange
	 * @throws BirtException
	 */
	public DateGroupCalculator(Object intervalStart, double intervalRange, ULocale locale ) throws BirtException
	{
		super( intervalStart, intervalRange );
		range = (int)Math.round( intervalRange );
		range = (range == 0 ? 1 : range);
		if ( intervalStart != null )
			this.intervalStart = DataTypeUtil.toDate( intervalStart );
		this.locale = locale == null ? ULocale.getDefault( ):locale;
		
		Calendar c = Calendar.getInstance( this.locale );
		c.clear( );
		c.set( 1970, 0, 1 );
		defaultStart = c.getTime( );
		
		this.dateTimeUtil = new DateTimeUtil( this.locale );
	}
	
	/**
	 * 
	 * @return
	 */
	protected int getDateIntervalRange()
	{
		return range;
	}
	
	protected Date getDate( Object value ) throws BirtException
	{
		return DataTypeUtil.toDate( value );
	}
}
