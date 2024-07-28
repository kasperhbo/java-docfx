/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/speech/v1/cloud_speech.proto

package com.microsoft.samples.google;

/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/speech/v1/cloud_speech.proto

import com.google.cloud.speech.v1.RecognitionAudioOrBuilder;
import com.google.protobuf.Message;

/**
 * <pre>
 * Contains audio data in the encoding specified in the `RecognitionConfig`.
 * Either `content` or `uri` must be supplied. Supplying both or neither
 * returns [google.rpc.Code.INVALID_ARGUMENT][google.rpc.Code.INVALID_ARGUMENT]. See
 * [content limits](https://cloud.google.com/speech-to-text/quotas#content).
 * </pre>
 * <p>
 * Protobuf type {@code google.cloud.speech.v1.RecognitionAudio}
 */
public final class RecognitionAudio extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:google.cloud.speech.v1.RecognitionAudio)
    RecognitionAudioOrBuilder {
  private static final long serialVersionUID = 0L;

  // Use RecognitionAudio.newBuilder() to construct.
  private RecognitionAudio(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  @Override
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return null;
  }

  private RecognitionAudio() {}

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
    return new RecognitionAudio();
  }

  @Override
  protected Message.Builder newBuilderForType(BuilderParent builderParent) {
    return null;
  }

  @Override
  public Message getDefaultInstanceForType() {
    return null;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }

  private RecognitionAudio(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10:
            {
              audioSourceCase_ = 1;
              audioSource_ = input.readBytes();
              break;
            }
          case 18:
            {
              java.lang.String s = input.readStringRequireUtf8();
              audioSourceCase_ = 2;
              audioSource_ = s;
              break;
            }
          default:
            {
              if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }

  private int audioSourceCase_ = 0;
  private java.lang.Object audioSource_;

  @Override
  public Message.Builder newBuilderForType() {
    return null;
  }

  @Override
  public Message.Builder toBuilder() {
    return null;
  }

    /**
     * The enum Audio source case.
     */
    public enum AudioSourceCase
      implements
          com.google.protobuf.Internal.EnumLite,
          com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        /**
         * Content audio source case.
         */
        CONTENT(1),
        /**
         * Uri audio source case.
         */
        URI(2),
        /**
         * Audiosource not set audio source case.
         */
        AUDIOSOURCE_NOT_SET(0);
    private final int value;

    private AudioSourceCase(int value) {
      this.value = value;
    }

        /**
         * Value of audio source case.
         *
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
    public static AudioSourceCase valueOf(int value) {
      return forNumber(value);
    }

        /**
         * For number audio source case.
         *
         * @param value the value
         * @return the audio source case
         */
        public static AudioSourceCase forNumber(int value) {
      switch (value) {
        case 1:
          return CONTENT;
        case 2:
          return URI;
        case 0:
          return AUDIOSOURCE_NOT_SET;
        default:
          return null;
      }
    }

    public int getNumber() {
      return this.value;
    }
  };

  public com.google.cloud.speech.v1.RecognitionAudio.AudioSourceCase getAudioSourceCase() {
    return com.google.cloud.speech.v1.RecognitionAudio.AudioSourceCase.forNumber(audioSourceCase_);
  }

    /**
     * The constant CONTENT_FIELD_NUMBER.
     */
    public static final int CONTENT_FIELD_NUMBER = 1;

  /**
   *
   *
   * <pre>
   * The audio data bytes encoded as specified in
   * `RecognitionConfig`. Note: as with all bytes fields, proto buffers use a
   * pure binary representation, whereas JSON representations use base64.
   * </pre>
   *
   * <code>bytes content = 1;</code>
   *
   * @return Whether the content field is set.
   */
  @java.lang.Override
  public boolean hasContent() {
    return audioSourceCase_ == 1;
  }

  /**
   *
   *
   * <pre>
   * The audio data bytes encoded as specified in
   * `RecognitionConfig`. Note: as with all bytes fields, proto buffers use a
   * pure binary representation, whereas JSON representations use base64.
   * </pre>
   *
   * <code>bytes content = 1;</code>
   *
   * @return The content.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getContent() {
    if (audioSourceCase_ == 1) {
      return (com.google.protobuf.ByteString) audioSource_;
    }
    return com.google.protobuf.ByteString.EMPTY;
  }

    /**
     * The constant URI_FIELD_NUMBER.
     */
    public static final int URI_FIELD_NUMBER = 2;

  /**
   *
   *
   * <pre>
   * URI that points to a file that contains audio data bytes as specified in
   * `RecognitionConfig`. The file must not be compressed (for example, gzip).
   * Currently, only Google Cloud Storage URIs are
   * supported, which must be specified in the following format:
   * `gs://bucket_name/object_name` (other URI formats return
   * [google.rpc.Code.INVALID_ARGUMENT][google.rpc.Code.INVALID_ARGUMENT]). For more information, see
   * [Request URIs](https://cloud.google.com/storage/docs/reference-uris).
   * </pre>
   *
   * <code>string uri = 2;</code>
   *
   * @return Whether the uri field is set.
   */
  public boolean hasUri() {
    return audioSourceCase_ == 2;
  }

  /**
   *
   *
   * <pre>
   * URI that points to a file that contains audio data bytes as specified in
   * `RecognitionConfig`. The file must not be compressed (for example, gzip).
   * Currently, only Google Cloud Storage URIs are
   * supported, which must be specified in the following format:
   * `gs://bucket_name/object_name` (other URI formats return
   * [google.rpc.Code.INVALID_ARGUMENT][google.rpc.Code.INVALID_ARGUMENT]). For more information, see
   * [Request URIs](https://cloud.google.com/storage/docs/reference-uris).
   * </pre>
   *
   * <code>string uri = 2;</code>
   *
   * @return The uri.
   */
  public java.lang.String getUri() {
    java.lang.Object ref = "";
    if (audioSourceCase_ == 2) {
      ref = audioSource_;
    }
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (audioSourceCase_ == 2) {
        audioSource_ = s;
      }
      return s;
    }
  }

  /**
   *
   *
   * <pre>
   * URI that points to a file that contains audio data bytes as specified in
   * `RecognitionConfig`. The file must not be compressed (for example, gzip).
   * Currently, only Google Cloud Storage URIs are
   * supported, which must be specified in the following format:
   * `gs://bucket_name/object_name` (other URI formats return
   * [google.rpc.Code.INVALID_ARGUMENT][google.rpc.Code.INVALID_ARGUMENT]). For more information, see
   * [Request URIs](https://cloud.google.com/storage/docs/reference-uris).
   * </pre>
   *
   * <code>string uri = 2;</code>
   *
   * @return The bytes for uri.
   */
  public com.google.protobuf.ByteString getUriBytes() {
    java.lang.Object ref = "";
    if (audioSourceCase_ == 2) {
      ref = audioSource_;
    }
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
      if (audioSourceCase_ == 2) {
        audioSource_ = b;
      }
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;

  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
    if (audioSourceCase_ == 1) {
      output.writeBytes(1, (com.google.protobuf.ByteString) audioSource_);
    }
    if (audioSourceCase_ == 2) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, audioSource_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (audioSourceCase_ == 1) {
      size +=
          com.google.protobuf.CodedOutputStream.computeBytesSize(
              1, (com.google.protobuf.ByteString) audioSource_);
    }
    if (audioSourceCase_ == 2) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, audioSource_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }
}
