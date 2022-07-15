package com.tim.taxcalculator;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public final static float qiZhengDian  = 5000f;
	public final static float tripleStandardYueXin = 25400.0f;
	
	public static float yueXin;
	public static float kouJian;
	public static float jiangJin;
	
	public static float yaoLaoXian;
	public static float yiLiaoXian;
	public static float shiYeXian;
	public static float gongJiJin;
	
	
	public static void main(String[] args) throws Exception {
		Map<String,Object> parameterMap = parseCommand(args);
		yueXin = Float.parseFloat(String.valueOf(parameterMap.get("-yueXin")));
		kouJian = Float.parseFloat(String.valueOf(parameterMap.get("-kouJian") == null?0:parameterMap.get("-kouJian")));
		jiangJin = Float.parseFloat(String.valueOf(parameterMap.get("-jiangjin")==null?0:parameterMap.get("-jiangjin")));
		System.out.println("\n注意核对，如不一致证明参数拼写错误： 月薪：" + yueXin + "	扣减：" + kouJian + "	奖金：" + jiangJin + "\n");
		
		float calculateFuLiByXinShui = 0.0f;
		if(yueXin > tripleStandardYueXin) {
			calculateFuLiByXinShui = tripleStandardYueXin;
		}else {
			calculateFuLiByXinShui = yueXin;
		}
		yaoLaoXian = calculateFuLiByXinShui * 0.08f;
		yiLiaoXian = calculateFuLiByXinShui * 0.02f;
		shiYeXian = calculateFuLiByXinShui * 0.002f;
		gongJiJin = calculateFuLiByXinShui * 0.12f;
		
		float fuliSum =  yaoLaoXian + yiLiaoXian + shiYeXian + gongJiJin + qiZhengDian + kouJian;
		float payForTax = yueXin - fuliSum;
		System.out.println("\n\n月薪:" + yueXin
				+ "\n社保：" + yaoLaoXian
				+ "\n医保：" + yiLiaoXian
				+ "\n失业：" + shiYeXian
				+ "\n公积金：" + gongJiJin
				+ "\n月需交税金额：" + payForTax + "\n\n"
				);		
		
		float taxSum = 0.0f;
		for(int i=1; i<=12; i++) {
			float payForTaxTemp = payForTax * (i * 1.0f);
			TaxRate taxRate = TaxRate.get(payForTaxTemp);
			float tax = payForTaxTemp * taxRate.taxRate - taxRate.suSuanKouJianShu - taxSum;
			taxSum += tax;
			System.out.println( i + "月份交税：" + tax + "		需交税金额：" + payForTaxTemp +"		税率：" + (int)(taxRate.taxRate * 100));
		}
		System.out.println("\n12个月总共收入：" + yueXin * 12.0f);
		System.out.println("12个月总共应缴税的所得额：" + payForTax * 12.0f);
		System.out.println("12月工资共交税：" + taxSum);
		
		float payForTaxSum = payForTax * 12.0f + jiangJin;
		TaxRate taxRate = TaxRate.get(payForTaxSum);
		float tax = payForTaxSum * taxRate.taxRate - taxRate.suSuanKouJianShu - taxSum;
		taxSum += tax;
		System.out.println("\n全年共收入（ +年终奖 ）：" + (yueXin * 12.0f + jiangJin));
		System.out.println("需交税金额（+ 年终奖）：" + (payForTaxSum));
		System.out.println( "年终奖交税：" + tax + "		税率：" + taxRate.taxRate * 100);
		System.out.println("全年共交税（+年终奖）：" + taxSum);
	}
	
	private static Map<String,Object> parseCommand(String[] args) {
		if(args.length == 0) {
			System.out.println("请按下列命令输入：\njava -jar taxRate.jar -yueXin 20000 -kouJian 2000 -jiangjin 30000");
			System.exit(0);
		}
		Map<String,Object> result = new HashMap<String,Object>();
		for(int i = 0;i<args.length;i++) {
			if("-yueXin".equalsIgnoreCase(args[i]) || "-kouJian".equalsIgnoreCase(args[i]) || "-jiangjin".equalsIgnoreCase(args[i])){
				result.put(args[i], args[++i]);
			}
		}
		if(!result.containsKey("-yueXin")) {
			System.out.println("请输入月薪金额，金额在参数 -yueXin 后方!");
			System.exit(0);
		}
		printlnCommand(args);
		return result;
	}
	
	private static void printlnCommand(String[] args) {
		StringBuilder command = new StringBuilder();
		command.append("java -jar taxRate.jar");
		for(int i =0;i<args.length;i++) {
			command.append(" " + args[i]);
		}
		System.out.println("\n您输入的命令为：" + command.toString());
	}

}
