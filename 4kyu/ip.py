# https://www.codewars.com/kata/63a0f3f49547e9001e7ad1ca/


class MyList(list):

    def __init__(self, parts):
        super().__init__(parts)

    def __getitem__(self, index):
        raise TypeError("Update operation is restricted for 'parts' field")


class IPv4Address:
    # Good luck, have fun
    def __init__(self, parts):
        """
        Creates an object representing an IPv4 address

        :param parts: a list of 4 integers
        """
        print(parts)
        self._parts = self.length(parts)

    @property
    def parts(self):
        return self._parts

    def __repr__(self):
        s = '.'.join([str(i) for i in self.parts])
        return s

    def __gt__(self, address_2):
        if isinstance(address_2, IPv4Address):
            return self._parts > address_2.parts
        else:
            raise ValueError

    def __lt__(self, address_2):
        if isinstance(address_2, IPv4Address):
            return self._parts < address_2.parts
        else:
            raise ValueError

    def __ge__(self, address_2):
        if isinstance(address_2, IPv4Address):
            return self._parts >= address_2.parts
        else:
            raise ValueError

    def __le__(self, address_2):
        if isinstance(address_2, IPv4Address):
            return self._parts <= address_2.parts
        else:
            raise ValueError

    def __eq__(self, address_2):
        if isinstance(address_2, IPv4Address):
            return self._parts == address_2.parts
        else:
            raise ValueError

    def length(self, parts):
        p = [1 if type(i) is int and 0 <= i <= 255 else 0 for i in parts]
        if len(parts) == 4 and parts[0] != 0 and all(p):
            return MyList(parts)
        else:
            raise ValueError

    def from_string(parts):
        res = []
        for i in parts.split('.'):
            res.append(int(i))
        return IPv4Address(res)

    def __hash__(self):
        return hash(tuple(self.parts))


print(IPv4Address([1, 2, 3, 4]))
address = IPv4Address.from_string('1.2.3.4')
print('result:', address.parts)
address1 = IPv4Address.from_string('1.2.3.4')
print(address1)
print(address == address1)
print(type(address.parts))
print(address.parts[0])
