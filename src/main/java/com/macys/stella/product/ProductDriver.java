package com.macys.stella.product;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import com.google.common.base.Preconditions;

public final class ProductDriver{
	
	private ProductDriver(){
		throw new AssertionError();
	}
	
	// API
	
	public static ProductByIdDriver goIntoRandomProduct( final ProductsPageDriver productsPageDriver ){
		Preconditions.checkNotNull( productsPageDriver );
		
		final ProductByIdDriver productDriver = productsPageDriver.goIntoProductByIndex( 1 );
		if( productDriver != null ){
			return productDriver;
		}
		
		return productsPageDriver.activateLeftMenuDriver().createProduct().fillRandom().create();
	}
	
	public static ProductCrossSellsDriver createCrossSell( final ProductByIdDriver productDriver ){
		return createCrossSell( productDriver, 1 );
	}
	public static ProductCrossSellsDriver createCrossSell( final ProductByIdDriver productDriver, final int positionOfCrossSells ){
		Preconditions.checkArgument( ( positionOfCrossSells > 0 ) && ( positionOfCrossSells <= 3 ) );
		
		final CrossSellCreationDriver crossSellCreationDriver = productDriver.openCrossSellsTab().startEditAndAddCrossSell();
		return crossSellCreationDriver.byDescription().changePosition( positionOfCrossSells ).value( "a" ).find().wait( 2 ).select( 1 ).done( positionOfCrossSells ).save();
	}
	
	public static ProductByIdDriver createAProduct( final ProductsPageDriver productsPageDriver ){
		final CreateProductDriver createProductDriver = productsPageDriver.activateLeftMenuDriver().createProduct();
		return createProductDriver.division( "1" ).department( "188" ).brandName( "A New York" ).projectId( "140" ).name( randomAlphabetic( 5 ) ).price( randomNumeric( 3 ) ).create();
	}
	
}
