/**
 * 
 * Copyright 2014 The Darks Learning Project (Liu lihua)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package darks.learning.common.utils;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;

public class MatrixHelper
{
	

	public static DoubleMatrix log(DoubleMatrix mt)
	{
		return MatrixFunctions.log(mt);
	}

	public static DoubleMatrix exp(DoubleMatrix mt)
	{
		return MatrixFunctions.exp(mt);
	}

	public static DoubleMatrix tanh(DoubleMatrix mt)
	{
		return MatrixFunctions.tanh(mt);
	}

	public static DoubleMatrix pow(DoubleMatrix mt, double e)
	{
		return MatrixFunctions.pow(mt, e);
	}

	public static DoubleMatrix sigmoid(DoubleMatrix mt)
	{
		DoubleMatrix ones = DoubleMatrix.ones(mt.rows, mt.columns);
		return ones.div(ones.add(exp(mt.neg())));
	}

	public static DoubleMatrix oneMinus(DoubleMatrix mt)
	{
		return DoubleMatrix.ones(mt.rows, mt.columns).sub(mt);
	}

	public static DoubleMatrix softmax(DoubleMatrix input)
	{
		DoubleMatrix max = input.rowMaxs();
		DoubleMatrix diff = MatrixFunctions.exp(input.subColumnVector(max));
		diff.diviColumnVector(diff.rowSums());
		return diff;
	}
}