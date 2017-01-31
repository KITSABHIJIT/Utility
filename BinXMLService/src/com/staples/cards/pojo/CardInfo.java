package com.staples.cards.pojo;

public class CardInfo {

	public String prefixLower,prefixUpper,cardLength,cardSubClass,cardClassName,provider,country;

	public CardInfo(String prefixLower, String prefixUpper, String cardLength,
			String cardSubClass, String cardClassName, String provider,
			String country) {
		super();
		this.prefixLower = prefixLower;
		this.prefixUpper = prefixUpper;
		this.cardLength = cardLength;
		this.cardSubClass = cardSubClass;
		this.cardClassName = cardClassName;
		this.provider = provider;
		this.country = country;
 	}

	public final String getPrefixLower() {
		return prefixLower;
	}

	public final void setPrefixLower(final String prefixLower) {
		this.prefixLower = prefixLower;
	}

	public final String getPrefixUpper() {
		return prefixUpper;
	}

	public final void setPrefixUpper(final String prefixUpper) {
		this.prefixUpper = prefixUpper;
	}

	public final String getCardLength() {
		return cardLength;
	}

	public final void setCardLength(final String cardLength) {
		this.cardLength = cardLength;
	}

	public final String getCardSubClass() {
		return cardSubClass;
	}

	public final void setCardSubClass(final String cardSubClass) {
		this.cardSubClass = cardSubClass;
	}

	public final String getCardClassName() {
		return cardClassName;
	}

	public final void setCardClassName(final String cardClassName) {
		this.cardClassName = cardClassName;
	}

	public final String getProvider() {
		return provider;
	}

	public final void setProvider(final String provider) {
		this.provider = provider;
	}

	public final String getCountry() {
		return country;
	}

	public final void setCountry(final String country) {
		this.country = country;
	}
}
