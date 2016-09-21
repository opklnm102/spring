package com.dong;

import java.io.InputStream;

public interface ArgumentResolver {
	Argument resolve(InputStream stream);
	
}
