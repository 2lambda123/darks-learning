/**
 * 
 * Copyright 2014 The Darks Learning Project (Liu lihua)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package darks.learning.neuron.gradient;

import org.jblas.DoubleMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import darks.learning.neuron.NNConfig;

/**
 * Gradient updater
 * 
 * @author Darks.Liu
 *
 */
public abstract class GradientComputer
{
	
	private static Logger log = LoggerFactory.getLogger(GradientComputer.class);
	
	int batchSize;
	
	DoubleMatrix wGradient;
	
	DoubleMatrix vGradient;
	
	DoubleMatrix hGradient;
	
	NNConfig config;
	
	AdaptiveLRGradient wAdaGrad;
	
	AdaptiveLRGradient vBiasAdaGrad;
	
	AdaptiveLRGradient hBiasAdaGrad;
	
	double numIterate;
	
	public GradientComputer(NNConfig config)
	{
		this.config = config;
	}
	
	/**
	 * Compute gradient values
	 * 
	 * @param wGrad Weights gradient
	 * @param vGrad Visible bias gradient
	 * @param hGrad Hidden bias gradient
	 */
	public abstract void computeGradient(DoubleMatrix wGrad, DoubleMatrix vBiasGrad, DoubleMatrix hBiasGrad);

	
	protected void buildAdaGrad(DoubleMatrix wGrad, DoubleMatrix vGrad, DoubleMatrix hGrad)
	{
		if (numIterate == 1)
		{
			if (wGrad != null && wAdaGrad == null)
			{
				wAdaGrad = new AdaptiveLRGradient(wGrad.rows, wGrad.columns);
			}
			if (vGrad != null && vBiasAdaGrad == null)
			{
				vBiasAdaGrad = new AdaptiveLRGradient(vGrad.rows, vGrad.columns);
			}
			if (hGrad != null && hBiasAdaGrad == null)
			{
				hBiasAdaGrad = new AdaptiveLRGradient(hGrad.rows, hGrad.columns);
			}
			if (log.isDebugEnabled())
			{
				log.debug("Build adaptive LR gradient.");
			}
		}
	}

	public DoubleMatrix getwGradient()
	{
		return wGradient;
	}

	public DoubleMatrix getvGradient()
	{
		return vGradient;
	}

	public DoubleMatrix gethGradient()
	{
		return hGradient;
	}

	public void setBatchSize(int batchSize)
	{
		this.batchSize = batchSize;
	}

	public double getNumIterate()
	{
		return numIterate;
	}

	public void setNumIterate(double numIterate)
	{
		this.numIterate = numIterate;
	}
	
	
}