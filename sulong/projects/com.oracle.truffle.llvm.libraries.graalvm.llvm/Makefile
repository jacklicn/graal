#
# Copyright (c) 2017, 2020, Oracle and/or its affiliates.
#
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without modification, are
# permitted provided that the following conditions are met:
#
# 1. Redistributions of source code must retain the above copyright notice, this list of
# conditions and the following disclaimer.
#
# 2. Redistributions in binary form must reproduce the above copyright notice, this list of
# conditions and the following disclaimer in the documentation and/or other materials provided
# with the distribution.
#
# 3. Neither the name of the copyright holder nor the names of its contributors may be used to
# endorse or promote products derived from this software without specific prior written
# permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
# OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
# MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
# COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
# GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
# AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
# OF THE POSSIBILITY OF SUCH DAMAGE.
#
QUIETLY$(MX_VERBOSE) = @

.PHONY: default
default: bin/${SONAME}

CLANG?=clang

CFLAGS+=-gdwarf-5 -fdebug-prefix-map=${VPATH}=com.oracle.truffle.llvm.libraries.graalvm.llvm
CPPFLAGS+=-I${VPATH}/include

ifeq ($(OS), darwin)
CLANG_PREFIX=/usr/bin/xcrun
LDFLAGS=-dynamiclib -gdwarf-5
LDFLAGS+=-Wl,-install_name,@rpath/$(notdir $@)
else
CLANG_PREFIX=
LDFLAGS=-shared -gdwarf-5 -Wl,-soname,$(notdir $@)
endif

LDFLAGS+= ${EXTRA_LDFLAGS}
CFLAGS=-gdwarf-5 -fPIC -DPIC -O3 ${EXTRA_CFLAGS}

bin/${SONAME}: bin/common.o bin/handles.o bin/polyglot.o bin/toolchain-api.o bin/truffle-compat.o
	$(QUIETLY) $(CLANG) ${LDFLAGS} -o $@ $+

bin/%.o: src/%.c
	@mkdir -p bin
	$(QUIETLY) $(CLANG) ${CPPFLAGS} ${CFLAGS} -c $< -o $@
