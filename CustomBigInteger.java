import java.util.*;
class CustomBigInteger{
    int digits[];
    boolean isResultNegative = false;
   
    boolean isNegative = false;
    CustomBigInteger(String s){
        int j=0;
        if(s.charAt(0)=='-'){
            isNegative = true;
            j++;
            
        }
        while(j<s.length() && s.charAt(j)=='0'){
            j++;
        }
        String st=s.substring(j);
        int n = st.length();
        digits = new int[n];
        for(int i = 0; i < n; i++){
            digits[i] = st.charAt(i) - '0';
        }
    }
    public String toString(){
        StringBuffer s = new StringBuffer();
        if(isResultNegative || isNegative){
            s.append('-');
        }
        for(int i = 0; i < digits.length; i++){
            s.append(this.digits[i]);
        }
        if(s.length() == 0){
            s.append('0');
        }
        return s.toString();
    }

    public CustomBigInteger add(CustomBigInteger other){
    	if(other.isZero() || this.isZero()) {
    		if(other.isZero()) {
    			if(this.isNegative) {
    				this.isResultNegative=true;
    				return this;
    			}
    			else return this;
    		}
    		else {
    			if(other.isNegative) {
    				other.isResultNegative=true;
    				return other;
    			}
    			return other;
    		}
    		
    	}
        if((this.isNegative&& !other.isNegative) || (!this.isNegative && other.isNegative)){
            if(this.compareTo(other)<0){
                String resultString = subtractHelper(other, this);
                CustomBigInteger resultBI = new CustomBigInteger(resultString);
                if(other.isNegative){
                    resultBI.isResultNegative = true;
                }
                return resultBI;
            }
            else if(this.compareTo(other)>0){
                String resultString = subtractHelper(this, other);
                CustomBigInteger resultBI = new CustomBigInteger(resultString);
                if(this.isNegative){
                    resultBI.isResultNegative = true;
                }
                return resultBI;
            }
            else{
                return new CustomBigInteger("0");
            }
        }
        int n=Math.max(other.digits.length,this.digits.length);
        int result[]=new int[n+1];
        int carry=0;
        for (int i = 0; i < n; i++) {
            int a = i < this.digits.length ? this.digits[this.digits.length - 1 - i] : 0;
            int b = i < other.digits.length ? other.digits[other.digits.length - 1 - i] : 0;
            int sum = a + b + carry;
            result[n - i] = sum % 10;
            carry = sum / 10;
        }
        result[0] = carry;

        String resultString = trimZero(result);

        CustomBigInteger resultBI = new CustomBigInteger(resultString);
        if(this.isNegative && other.isNegative){
            resultBI.isResultNegative = true;
        }

        return resultBI;

    }

    private String subtractHelper(CustomBigInteger num1, CustomBigInteger num2){
        int borrow=0;
        int n=num1.digits.length;
        int result[]=new int[n+1];
        for (int i = 0; i < n; i++) {
            int a = i < num1.digits.length ? num1.digits[num1.digits.length - 1 - i] : 0;
            int b = i < num2.digits.length ? num2.digits[num2.digits.length - 1 - i] : 0;
            int sum = a - b - borrow;
            if(sum<0){
                sum+=10;
                borrow=1;
            }
            else{
                borrow=0;
            }
            result[n - i] = sum;
        }

        String resultString = trimZero(result);
        if(resultString.length() == 0){
            resultString = "0";
        }
        return resultString;
    }              
    public CustomBigInteger subtract(CustomBigInteger other){
    	if(other.isZero() || this.isZero()) {
    		if(other.isZero()) {
    			if(this.isNegative) {
    				this.isResultNegative=true;
    				return this;
    			}
    			else return this;
    		}
    		else {
    			if(!other.isNegative) {
    				other.isResultNegative=true;
    				return other;
    			}else{
    				String resultString = trimZero(other.digits);
    				CustomBigInteger result= new CustomBigInteger(resultString);
    				return result;
    				
    			}
    		}
    		
    	}
        if(!this.isNegative && other.isNegative){
            other.isNegative = false;
            CustomBigInteger res = this.add(other);
            other.isNegative = true;
            return res;
        }
        else if(this.isNegative && !other.isNegative){
            other.isNegative = true;
            CustomBigInteger res = this.add(other);
            other.isNegative = false;
            return res;
        }
        else if(this.isNegative && other.isNegative){
            if(compareTo(other) < 0){
                String resultString = subtractHelper(other, this);
                CustomBigInteger num = new CustomBigInteger(resultString);
                return num;
            }
            else if(compareTo(other) > 0){
                String resultString = subtractHelper(this, other);
                CustomBigInteger num = new CustomBigInteger(resultString);
                num.isResultNegative = true;
                return num;

            }
            else{
                return new CustomBigInteger("0");
            }
        }

        int n=Math.max(other.digits.length,this.digits.length);
       
        int checkGreater=this.compareTo(other);

        if(checkGreater< 0){
            String resultString = subtractHelper(other, this);
            CustomBigInteger num = new CustomBigInteger(resultString.toString());
            num.isResultNegative = true;
            return num;
            
        }
        else{
            String resultString = subtractHelper(this, other);

            return new CustomBigInteger(resultString);
        }        

    }

    public CustomBigInteger multiply(CustomBigInteger other){
    	if(this.isZero() || other.isZero()) {
    		return new CustomBigInteger("0");
    	}
    	
        int n= this.digits.length + other.digits.length;
        int[] result = new int[n];
       for(int i = this.digits.length-1; i >= 0; i--){
           for(int j = other.digits.length-1; j >= 0; j--){
               int mul = this.digits[i]  * other.digits[j];
               int p1 = i + j;
               int p2 = i + j + 1;
               int sum = mul + result[p2];
               result[p1] += sum / 10;
               result[p2] = sum % 10;
           }
       }
       String resultString = trimZero(result);
       CustomBigInteger res = new CustomBigInteger(resultString);
       if(this.isNegative != other.isNegative){
           res.isResultNegative = true;
       }
       return res;
    }
    private String trimZero(int []result){
       StringBuffer resultString = new StringBuffer();
       for (int i = 0; i < result.length; i++) {
           if(result[i]==0){
               continue;
           }
           else{
               for (int j = i; j < result.length; j++) {
                   resultString.append(result[j]);
               }
               break;
           }
       }
       return resultString.toString();
    }

    public CustomBigInteger divide(CustomBigInteger divisor) {
        if (divisor.isZero()) {
            throw new ArithmeticException("Division by zero");
        }
        
        
        CustomBigInteger absThis = this.abs();
        CustomBigInteger absDivisor = divisor.abs();
        if (absThis.compareTo(absDivisor) < 0) {
            return new CustomBigInteger("0");
        }

        CustomBigInteger quotient = new CustomBigInteger("0");
        CustomBigInteger remainder = new CustomBigInteger("0");

        for (int i = 0; i < absThis.digits.length; i++) {
            remainder = remainder.multiply(new CustomBigInteger("10"))
                                 .add(new CustomBigInteger(String.valueOf(absThis.digits[i])));
            int digit = 0;
           
            while (remainder.compareTo(absDivisor) >= 0) {
                remainder = remainder.subtract(absDivisor);
                digit++;
            }

            quotient = quotient.multiply(new CustomBigInteger("10")).add(new CustomBigInteger(String.valueOf(digit)));
            
        }


        if(this.isNegative != divisor.isNegative){
            quotient.isResultNegative = true;
        }
        

        return quotient;
    }
    public CustomBigInteger abs() {
        CustomBigInteger result = new CustomBigInteger(this.toString());
        result.isNegative = false;
        
        return result;
    }
    
    // if(this> other) result will be 1
    public int compareTo(CustomBigInteger other) {
        
    
        if (this.digits.length != other.digits.length) {
            return this.digits.length>other.digits.length ? 
                   1:-1;
        }
    
        for (int i = 0; i < this.digits.length; i++) {
            if (this.digits[i] != other.digits[i]) {
                return this.digits[i]>other.digits[i]? 
                       1 :-1;
            }
        }
    
        return 0;
    }
    
    private boolean isZero() {
        for (int digit : digits) {
            if (digit != 0) return false;
        }
        return true;
    }
    

}
