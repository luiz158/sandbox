package com.macys.stella.product;

import com.google.common.base.Preconditions;

public final class ProductShortcut{
	
	private ProductShortcut(){
		throw new AssertionError();
	}
	
	// API
	
	public static ProductByIdDriver goIntoRandomProduct( final ProductsPageDriver productsPageDriver ){
		Preconditions.checkNotNull( productsPageDriver );
		
		final ProductByIdDriver productDriver = productsPageDriver.goIntoEntityByIndex( 1 );
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
		return createProductDriver.fillRandom().create();
	}
	
}
