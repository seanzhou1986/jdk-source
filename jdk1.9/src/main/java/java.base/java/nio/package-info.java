/*
 * Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/**
 * Defines buffers, which are containers for data, and provides an
 * overview of the other NIO packages.
 *
 *
 * <p> The central abstractions of the NIO APIs are: </p>
 *
 * <ul>
 *
 *   <li><p> <a href="#buffers"><i>Buffers</i></a>, which are containers for data;
 *   </p></li>
 *
 *   <li><p> <a
 *   href="charset/package-summary.html"><i>Charsets</i></a> and their
 *   associated <i>decoders</i> and <i>encoders</i>, <br> which
 *   translate between bytes and Unicode characters; </p></li>
 *
 *   <li><p> <a
 *   href="channels/package-summary.html"><i>Channels</i></a> of
 *   various types, which represent connections <br> to entities
 *   capable of performing I/O operations; and </p></li>
 *
 *   <li><p> <i>Selectors</i> and <i>selection keys</i>, which
 *   together with <br> <i>selectable channels</i> define a <a
 *   href="channels/package-summary.html#multiplex">multiplexed,
 *   non-blocking <br> I/O</a>&nbsp;facility.  </p></li>
 *
 *  </ul>
 *
 * <p> The {@code java.nio} package defines the buffer classes, which
 * are used throughout the NIO APIs.  The charset API is defined in
 * the {@link java.nio.charset} package, and the channel and selector
 * APIs are defined in the {@link java.nio.channels} package.  Each of
 * these subpackages has its own service-provider (SPI) subpackage,
 * the contents of which can be used to extend the platform's default
 * implementations or to construct alternative implementations.
 *
 * <a id="buffers"> </a>
 *
 * <blockquote><table class="borderless">
 *     <caption style="display:none">Description of the various buffers</caption>
 *   <tr><th style="text-align:left">Buffers</th>
 *       <th style="text-align:left">Description</th></tr>
 *   <tr><td style="vertical-align:top">{@link java.nio.Buffer}</td>
 *       <td>Position, limit, and capacity;
 *           <br>clear, flip, rewind, and mark/reset</td></tr>
 *   <tr><td style="vertical-align:top">&nbsp;&nbsp;{@link java.nio.ByteBuffer}</td>
 *       <td>Get/put, compact, views; allocate,&nbsp;wrap</td></tr>
 *   <tr><td style="vertical-align:top">
 *       &nbsp;&nbsp;&nbsp;&nbsp;{@link java.nio.MappedByteBuffer}&nbsp;&nbsp;</td>
 *       <td>A byte buffer mapped to a file</td></tr>
 *   <tr><td style="vertical-align:top">&nbsp;&nbsp;{@link java.nio.CharBuffer}</td>
 *       <td>Get/put, compact; allocate,&nbsp;wrap</td></tr>
 *   <tr><td style="vertical-align:top">&nbsp;&nbsp;{@link java.nio.DoubleBuffer}</td>
 *       <td>&nbsp;&nbsp;&nbsp;&nbsp;'&nbsp;'</td></tr>
 *   <tr><td style="vertical-align:top">&nbsp;&nbsp;{@link java.nio.FloatBuffer}</td>
 *       <td>&nbsp;&nbsp;&nbsp;&nbsp;'&nbsp;'</td></tr>
 *   <tr><td style="vertical-align:top">&nbsp;&nbsp;{@link java.nio.IntBuffer}</td>
 *       <td>&nbsp;&nbsp;&nbsp;&nbsp;'&nbsp;'</td></tr>
 *   <tr><td style="vertical-align:top">&nbsp;&nbsp;{@link java.nio.LongBuffer}</td>
 *       <td>&nbsp;&nbsp;&nbsp;&nbsp;'&nbsp;'</td></tr>
 *   <tr><td style="vertical-align:top">&nbsp;&nbsp;{@link java.nio.ShortBuffer}</td>
 *       <td>&nbsp;&nbsp;&nbsp;&nbsp;'&nbsp;'</td></tr>
 *   <tr><td style="vertical-align:top">{@link java.nio.ByteOrder}</td>
 *       <td>Typesafe enumeration for&nbsp;byte&nbsp;orders</td></tr>
 * </table></blockquote>
 *
 * <p> A <i>buffer</i> is a container for a fixed amount of data of a
 * specific primitive type.  In addition to its content a buffer has a
 * <i>position</i>, which is the index of the next element to be read
 * or written, and a <i>limit</i>, which is the index of the first
 * element that should not be read or written.  The base {@link
 * java.nio.Buffer} class defines these properties as well as methods
 * for <i>clearing</i>, <i>flipping</i>, and <i>rewinding</i>, for
 * <i>marking</i> the current position, and for <i>resetting</i> the
 * position to the previous mark.
 *
 * <p> There is a buffer class for each non-boolean primitive type.
 * Each class defines a family of <i>get</i> and <i>put</i> methods
 * for moving data out of and in to a buffer, methods for
 * <i>compacting</i>, <i>duplicating</i>, and <i>slicing</i> a buffer,
 * and static methods for <i>allocating</i> a new buffer as well as
 * for <i>wrapping</i> an existing array into a buffer.
 *
 * <p> Byte buffers are distinguished in that they can be used as the
 * sources and targets of I/O operations.  They also support several
 * features not found in the other buffer classes:
 *
 * <ul>
 *
 *   <li><p> A byte buffer can be allocated as a <a
 *   href="ByteBuffer.html#direct"> <i>direct</i></a> buffer, in which
 *   case the Java virtual machine will make a best effort to perform
 *   native I/O operations directly upon it.  </p></li>
 *
 *   <li><p> A byte buffer can be created by {@link
 *   java.nio.channels.FileChannel#map <i>mapping</i>} a region of a
 *   file directly into memory, in which case a few additional
 *   file-related operations defined in the {@link
 *   java.nio.MappedByteBuffer} class are available.  </p></li>
 *
 *   <li><p> A byte buffer provides access to its content as either a
 *   heterogeneous or homogeneous sequence of <a
 *   href="ByteBuffer.html#bin"><i>binary data</i></a> of any
 *   non-boolean primitive type, in either big-endian or little-endian
 *   <a href="ByteOrder.html">byte order</a>.  </p></li>
 *
 * </ul>
 *
 * <p> Unless otherwise noted, passing a {@code null} argument to a
 * constructor or method in any class or interface in this package
 * will cause a {@link java.lang.NullPointerException
 * NullPointerException} to be thrown.
 *
 * @since 1.4
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 */
package java.nio;
