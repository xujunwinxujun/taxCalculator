package com.tim.taxcalculator;

public class TaxRate {
	

	public static TaxRate get(float payForTax) {
		float _0k = 0.0f;
		float _36k = 36000.0f;
		float _144k = 144000.0f;
		float _300k = 300000.0f;
		float _420k = 420000.0f;
		float _660k = 660000.0f;
		float _960k = 960000.0f;
		float _maxk = Float.MAX_VALUE;
		
		TaxRate result = null;
		if(payForTax > _0k && payForTax <= _36k) {
			result = new TaxRate(1,_0k,_36k,0.03f,0f);
		}else if(payForTax <= _144k) {
			result = new TaxRate(2,_36k,_144k,0.10f,210.0f * 12.0f);
		}else if(payForTax <= _300k) {
			result = new TaxRate(3,_144k,_300k,0.20f,1410.0f * 12.0f);
		}else if(payForTax <= _420k) {
			result = new TaxRate(4,_300k,_420k,0.25f,2660.0f * 12.0f);
		}else if(payForTax <= _660k) {
			result = new TaxRate(5,_420k,_660k,0.30f,4410.0f * 12.0f);
		}else if(payForTax <= _960k) {
			result = new TaxRate(6,_660k,_960k,0.35f,7160.0f * 12.0f);
		}else if(payForTax > _960k){
			result = new TaxRate(7,_960k,_maxk,0.45f,15160.0f * 12.0f);
		}			
		return result;
	}
	
	private TaxRate(int level,float lessThanMoney,float moreThanMoney,float taxRate,float suSuanKouJianShu) {
		this.level = level;
	    this.lessThanMoney = lessThanMoney;
		this.moreThanMoney = moreThanMoney;
		this.taxRate = taxRate;
		this.suSuanKouJianShu = suSuanKouJianShu;
	}
	
	public int level;
	public float lessThanMoney;
	public float moreThanMoney;
	public float taxRate;
	public float suSuanKouJianShu;

}
