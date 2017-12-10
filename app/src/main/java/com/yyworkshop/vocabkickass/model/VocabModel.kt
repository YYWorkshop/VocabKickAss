package com.yyworkshop.vocabkickass.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by hulonelyy on 2017/12/6.
 */

class VocabModel() : Parcelable{

    var id: Long = 0
    var vocab: String? = null
    var definition: String? = null
    var status: Int = 0
    var dictsName: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        vocab = parcel.readString()
        definition = parcel.readString()
        status = parcel.readInt()
        dictsName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(vocab)
        parcel.writeString(definition)
        parcel.writeInt(status)
        parcel.writeString(dictsName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VocabModel> {
        override fun createFromParcel(parcel: Parcel): VocabModel {
            return VocabModel(parcel)
        }

        override fun newArray(size: Int): Array<VocabModel?> {
            return arrayOfNulls(size)
        }
    }
}
